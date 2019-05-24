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
    private BorrowingService borrowingService;
    private BorrowedBookMapper borrowedBookMapper;
    private BorrowedBookService borrowedBookService;

    @Autowired
    public BorrowingController(BorrowingService borrowingService, BorrowedBookMapper borrowedBookMapper,
                               BorrowedBookService borrowedBookService) {
        this.borrowingService = borrowingService;
        this.borrowedBookMapper = borrowedBookMapper;
        this.borrowedBookService = borrowedBookService;
    }

    @GetMapping(value = "getBorrowedBooks")
    public List<BorrowedBookDto> getBorrowedBooks(){
       return borrowedBookMapper.mapToBorrowedBookDtoList(borrowedBookService.getBorrowedBooks());
    }

    @PostMapping(value = "borrowBook")
    public void borrowBook(@RequestParam("bookId") Long bookId, @RequestParam("readerId") Long readerId) {
        borrowingService.borrowBook(bookId, readerId);
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestParam("copyId")Long copyId){
        borrowingService.returnBook(copyId);
    }
}
