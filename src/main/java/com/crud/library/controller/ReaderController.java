package com.crud.library.controller;

import com.crud.library.domain.ReaderDto;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class ReaderController {
    private final ReaderService readerService;
    private final ReaderMapper readerMapper;

    @Autowired
    public ReaderController(ReaderService readerService,
                            ReaderMapper readerMapper) {
        this.readerService = readerService;
        this.readerMapper = readerMapper;
    }

    @GetMapping(value = "readers")
    public List<ReaderDto> getReaders() {
        return readerMapper.mapToReaderDtoList(readerService.findAll());
    }

    @GetMapping(value = "readers/{readerId}")
    public ReaderDto getReaders(@PathVariable Long readerId) {
        return readerMapper.mapToReaderDto(readerService.findById(readerId));
    }

    @PostMapping(value = "readers", consumes = APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto) {
        readerService.save(readerMapper.mapToReader(readerDto));
    }
}
