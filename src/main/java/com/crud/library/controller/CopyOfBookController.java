package com.crud.library.controller;

import com.crud.library.domain.CopyOfTheBook;
import com.crud.library.domain.CopyOfTheBookDto;
import com.crud.library.domain.Status;
import com.crud.library.mapper.CopyOfTheBookMapper;
import com.crud.library.service.CopyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library/copy")
public class CopyOfBookController {

    private CopyService copyService;
    private CopyOfTheBookMapper copyOfTheBookMapper;

    public CopyOfBookController(CopyService copyService, CopyOfTheBookMapper copyOfTheBookMapper) {
        this.copyService = copyService;
        this.copyOfTheBookMapper = copyOfTheBookMapper;
    }

    @GetMapping(value = "howManyAccessToBorrow")
    public Integer getAccessCopyOfTheBook(@RequestParam("bookId") Long bookId) {
        return copyService.howManyAccessToBorrow(bookId);
    }

    @PostMapping(value = "addCopy")
    public void addCopyOfBook(@RequestParam("bookId") Long bookId) {
        copyService.addCopyOfTheBook(bookId);
    }

    @DeleteMapping(value = "deleteCopy")
    public void deleteCopyOfBook(@RequestParam("copyId") Long copyOfTheBookId) {
        copyService.deleteCopyOfTheBookById(copyOfTheBookId);
    }

    @PutMapping(value = "destroyed")
    public CopyOfTheBookDto changeStatusOnDestroyed(@RequestParam("copyId") Long copyOfTheBookId) {
        CopyOfTheBook copy = copyService.changeStatus(copyOfTheBookId, Status.DESTROYED);
        return copyOfTheBookMapper.mapTocopyOfTheBookDto(copy);
    }

    @PutMapping(value = "lost")
    public CopyOfTheBookDto changeStatusOnLost(@RequestParam("copyId") Long copyOfTheBookId) {
        CopyOfTheBook copy = copyService.changeStatus(copyOfTheBookId, Status.LOST);
        return copyOfTheBookMapper.mapTocopyOfTheBookDto(copy);
    }
}
