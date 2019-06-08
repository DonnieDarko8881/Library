package com.crud.library.service;

import com.crud.library.Exception.BorrowedBookNotFoundException;
import com.crud.library.Exception.CopyNotFoundException;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BorrowedBook;
import com.crud.library.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingService {
    private final BookService bookService;
    private final BorrowedBookService borrowedBookService;
    private final CopyService copyService;
    private final ReaderService readerService;

    @Autowired
    public BorrowingService(BookService bookService,
                            BorrowedBookService borrowedBookService,
                            CopyService copyService,
                            ReaderService readerService) {
        this.bookService = bookService;
        this.borrowedBookService = borrowedBookService;
        this.copyService = copyService;
        this.readerService = readerService;
    }

    public void borrowBook(Long bookId, Long readerId) throws CopyNotFoundException {
        BookCopy copyToBorrow = bookService.findById(bookId)
                .getCopiesBookInLibrary().stream()
                .filter(copy -> !copy.isBorrowed())
                .filter(status -> status.getStatus().equals(Status.GOOD.toString()))
                .findFirst().orElseThrow(CopyNotFoundException::new);
        copyToBorrow.setBorrowed(true);
        borrowedBookService.save(new BorrowedBook(copyToBorrow, readerService.findById(readerId), LocalDate.now()));
    }

    public void returnBook(Long copyId) throws BorrowedBookNotFoundException {
        BookCopy copyToReturn = copyService.findById(copyId);
        BorrowedBook borrowedBook = borrowedBookService.findAll().stream()
                .filter(book -> book.getCopyOfTheBook().getId().equals(copyId))
                .findFirst().orElseThrow(BorrowedBookNotFoundException::new);
        copyToReturn.setBorrowed(false);
        borrowedBook.setReturnDate(LocalDate.now());
        borrowedBookService.save(borrowedBook);
    }
}
