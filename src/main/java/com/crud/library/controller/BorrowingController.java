package com.crud.library.controller;

import com.crud.library.domain.BorrowedBookDto;
import com.crud.library.mapper.BorrowedBookMapper;
import com.crud.library.service.BorrowedBookService;
import com.crud.library.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class BorrowingController {
    private final BorrowingService borrowingService;
    private final BorrowedBookMapper borrowedBookMapper;
    private final BorrowedBookService borrowedBookService;

    @Autowired
    public BorrowingController(BorrowingService borrowingService,
                               BorrowedBookMapper borrowedBookMapper,
                               BorrowedBookService borrowedBookService) {
        this.borrowingService = borrowingService;
        this.borrowedBookMapper = borrowedBookMapper;
        this.borrowedBookService = borrowedBookService;
    }

    @GetMapping(value = "/borrowedBooks")
    public List<BorrowedBookDto> getBorrowedBooks() {
        return borrowedBookMapper.mapToBorrowedBookDtoList(borrowedBookService.findAll());
    }

    @PostMapping(value = "/borrowBook/books/{bookId}/readers/{readerId}")
    public void borrowBook(@PathVariable Long bookId, @PathVariable Long readerId) {
        borrowingService.borrowBook(bookId, readerId);
    }

    @PutMapping(value = "/returnBook/copies/{copyId}")
    public void returnBook(@PathVariable Long copyId) {
        borrowingService.returnBook(copyId);
    }
}
