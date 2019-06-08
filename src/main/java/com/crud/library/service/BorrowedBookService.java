package com.crud.library.service;

import com.crud.library.domain.BorrowedBook;
import com.crud.library.repository.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowedBookService {
    private final BorrowedBookRepository bookRepository;

    @Autowired
    public BorrowedBookService(BorrowedBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(BorrowedBook borrowedBook) {
        bookRepository.save(borrowedBook);
    }

    public void deleteById(long borrowedBookId) {
        bookRepository.deleteById(borrowedBookId);
    }

    public List<BorrowedBook> findAll() {
        return bookRepository.findAll();
    }
}
