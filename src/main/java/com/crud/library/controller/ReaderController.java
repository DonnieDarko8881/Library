package com.crud.library.controller;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/reader")
public class ReaderController {
    private ReaderService readerService;
    private ReaderMapper readerMapper;

    @Autowired
    public ReaderController(ReaderService readerService, ReaderMapper readerMapper) {
        this.readerService = readerService;
        this.readerMapper = readerMapper;
    }

    @GetMapping(value = "getReaders")
    public List<ReaderDto> getReaders(){
        return readerMapper.maptoReaderDtoList(readerService.getReaders());
    }

    @GetMapping(value = "getReader")
    public ReaderDto getReaders(@RequestParam("readerId") Long readerId){
        return readerMapper.maptoReaderDto(readerService.findReaderById(readerId));
    }

    @PostMapping(value = "addReader", consumes = APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto){
        readerService.saveReader(readerMapper.mapToReader(readerDto));
    }
}
