package com.crud.library.service;

import com.crud.library.Exception.BorredBookNotFoundException;
import com.crud.library.Exception.CopyNotFoundException;
import com.crud.library.domain.BorrowedBook;
import com.crud.library.domain.CopyOfTheBook;
import com.crud.library.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class BorrowingService {

    private BookService bookService;
    private BorrowedBookService borrowedBookService;
    private CopyService copyService;
    private ReaderService readerService;

    @Autowired
    public BorrowingService(BookService bookService, BorrowedBookService borrowedBookService,
                            CopyService copyService, ReaderService readerService) {
        this.bookService = bookService;
        this.borrowedBookService = borrowedBookService;
        this.copyService = copyService;
        this.readerService = readerService;
    }

    public void borrowBook(Long bookId, Long readerId) throws CopyNotFoundException {
        CopyOfTheBook copyToBorrow = bookService.findBookById(bookId)
                .getCopiesBookInLibrary().stream()
                .filter(copy -> !copy.getBorrowed())
                .filter(status -> status.getStatus().equals(Status.GOOD.toString()))
                .findFirst().orElseThrow(CopyNotFoundException::new);
        copyToBorrow.setBorrowed(true);
        borrowedBookService.saveBorrowedBook(new BorrowedBook(copyToBorrow, readerService.findReaderById(readerId), LocalDate.now()));
    }

    public void returnBook(Long copyId) throws BorredBookNotFoundException {
        CopyOfTheBook copyToReturn = copyService.findCopyOfTheBookById(copyId);
        BorrowedBook borrowedBook = borrowedBookService.getBorrowedBooks().stream()
                .filter(borrowedBook1 -> borrowedBook1.getCopyOfTheBook().getId() == copyId)
                .findFirst().orElseThrow(BorredBookNotFoundException::new);
        copyToReturn.setBorrowed(false);
        borrowedBook.setReturnDate(LocalDate.now());
        borrowedBookService.saveBorrowedBook(borrowedBook);
    }
}
