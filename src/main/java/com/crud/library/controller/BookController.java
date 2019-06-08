package com.crud.library.controller;

import com.crud.library.domain.BookDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class BookController {
    private final BookMapper bookMapper;
    private final BookService bookService;

    @Autowired
    public BookController(BookMapper bookMapper,
                          BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @GetMapping(value = "/books")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(bookService.getBooks());
    }

    @GetMapping(value = "/books/{bookId}")
    public BookDto getBook(@PathVariable Long bookId) {
        return bookMapper.mapToBookDto(bookService.findById(bookId));
    }

    @PostMapping(value = "/books", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.save(bookMapper.mapToBook(bookDto));
    }
}
