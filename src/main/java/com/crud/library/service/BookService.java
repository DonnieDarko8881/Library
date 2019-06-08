package com.crud.library.service;

import com.crud.library.Exception.BookNotFoundException;
import com.crud.library.domain.Book;
import com.crud.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book findById(long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }
}
