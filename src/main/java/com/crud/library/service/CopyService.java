package com.crud.library.service;

import com.crud.library.Exception.CopyNotFoundException;
import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Status;
import com.crud.library.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyService {

    private final CopyRepository copyRepository;
    private final BookService bookService;

    @Autowired
    public CopyService(CopyRepository copyRepository, BookService bookService) {
        this.copyRepository = copyRepository;
        this.bookService = bookService;
    }

    public void save(BookCopy copyOfTheBook) {
        copyRepository.save(copyOfTheBook);
    }

    public void deleteBookById(Long copyOfTheBookId) {
        copyRepository.deleteById(copyOfTheBookId);
    }

    public BookCopy findById(Long copyOfTheBookId) {
        return copyRepository.findById(copyOfTheBookId).orElseThrow(CopyNotFoundException::new);
    }

    public Integer howManyAccessToBorrow(Long bookId) {
        return copyRepository.retrieveAmountAccessBookToBorrow(bookId).size();
    }

    public void addBookCopy(Long bookId) {
        Book book = bookService.findById(bookId);
        BookCopy copy = new BookCopy(book, Status.GOOD.toString());
        copy.setBook(book);
        save(copy);
    }

    public BookCopy changeStatus(Long copyOfTheBookId, String status) {
        BookCopy copy = findById(copyOfTheBookId);
        copy.setStatus(status);
        save(copy);
        return copy;
    }
}

