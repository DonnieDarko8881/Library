package com.crud.library.service;

import com.crud.library.domain.BorrowedBook;
import com.crud.library.repository.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BorrowedBookService {

    private BorrowedBookRepository bookRepository;

    @Autowired
    public BorrowedBookService(BorrowedBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBorrowedBook(BorrowedBook borrowedBook) {
        bookRepository.save(borrowedBook);
    }

    public void deleteBorrowBookById(long borrowedBookId) {
        bookRepository.deleteById(borrowedBookId);
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return bookRepository.findAll();
    }
}
