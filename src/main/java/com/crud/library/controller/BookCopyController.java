package com.crud.library.controller;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookCopyDto;
import com.crud.library.domain.Status;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library")
public class BookCopyController {

    private final CopyService copyService;
    private final BookCopyMapper bookCopyMapper;

    @Autowired
    public BookCopyController(CopyService copyService,
                              BookCopyMapper bookCopyMapper) {
        this.copyService = copyService;
        this.bookCopyMapper = bookCopyMapper;
    }

    @GetMapping(value = "/copies/howManyAccessToBorrow/{bookId}")
    public Integer getAccessCopyOfTheBook(@PathVariable long bookId) {
        return copyService.howManyAccessToBorrow(bookId);
    }

    @PostMapping(value = "/copies/books/{bookId}")
    public void addCopyOfBook(@PathVariable Long bookId) {
        copyService.addBookCopy(bookId);
    }

    @DeleteMapping(value = "/copies/{bookCopyId}")
    public void deleteCopyOfBook(@PathVariable Long bookCopyId) {
        copyService.deleteBookById(bookCopyId);
    }

    @PutMapping(value = "/copies/{bookCopyId}/destroy")
    public BookCopyDto changeStatusOnDestroyed(@PathVariable Long bookCopyId) {
        BookCopy bookCopy = copyService.changeStatus(bookCopyId, Status.DESTROYED.toString());
        return bookCopyMapper.mapToCopyOfTheBookDto(bookCopy);
    }

    @PutMapping(value = "/copies/{bookCopyId}/lost")
    public BookCopyDto changeStatusOnLost(@PathVariable Long bookCopyId) {
        BookCopy copy = copyService.changeStatus(bookCopyId, Status.LOST.toString());
        return bookCopyMapper.mapToCopyOfTheBookDto(copy);
    }
}
