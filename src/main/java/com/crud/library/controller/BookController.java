package com.crud.library.controller;


import com.crud.library.domain.BookDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/book")
public class BookController {
    private BookMapper bookMapper;
    private BookService bookService;

    @Autowired
    public BookController(BookMapper bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @GetMapping(value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(bookService.getBooks());
    }

    @GetMapping(value = "getBook")
    public BookDto getBooks(@RequestParam("bookId") Long bookId) {
        return bookMapper.mapToBookDto(bookService.findBookById(bookId));
    }

    @PostMapping(value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto ) {
        bookService.saveBook(bookMapper.mapToBook(bookDto));
    }




}
