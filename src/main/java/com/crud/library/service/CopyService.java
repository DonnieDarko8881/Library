package com.crud.library.service;

import com.crud.library.Exception.CopyNotFoundException;
import com.crud.library.domain.Book;
import com.crud.library.domain.CopyOfTheBook;
import com.crud.library.domain.Status;
import com.crud.library.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CopyService {

    private CopyRepository copyRepository;
    private BookService bookService;

    @Autowired
    public CopyService(CopyRepository copyRepository, BookService bookService) {
        this.copyRepository = copyRepository;
        this.bookService = bookService;
    }

    public void saveCopyOfTheBook(CopyOfTheBook copyOfTheBook) {
        copyRepository.save(copyOfTheBook);
    }

    public void deleteCopyOfTheBookById(Long copyOfTheBookId) {
        copyRepository.deleteById(copyOfTheBookId);
    }

    public CopyOfTheBook findCopyOfTheBookById(Long copyOfTheBookId) {
        return copyRepository.findById(copyOfTheBookId).orElseThrow(CopyNotFoundException::new);
    }

    public Integer howManyAccessToBorrow(Long bookId) {
        return (int) bookService.findBookById(bookId).getCopiesBookInLibrary().stream()
                .filter(copy -> !copy.getBorrowed())
                .filter(copy -> copy.getStatus().equals(Status.GOOD.toString()))
                .count();
    }

    public void addCopyOfTheBook(Long bookId) {
        CopyOfTheBook copy = new CopyOfTheBook(bookService.findBookById(bookId), Status.GOOD.toString());
        Book book = bookService.findBookById(bookId);
        copy.setBook(book);
        saveCopyOfTheBook(copy);
    }

    public CopyOfTheBook changeStatus(Long copyOfTheBookId, Status status) {
        CopyOfTheBook copy = findCopyOfTheBookById(copyOfTheBookId);
        copy.setStatus(status.toString());
        saveCopyOfTheBook(copy);
        return copy;
    }
}

