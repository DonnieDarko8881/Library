package com.crud.library.service;

import com.crud.library.Exception.ReaderNotFoundException;
import com.crud.library.domain.Reader;
import com.crud.library.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public void save(Reader reader) {
        readerRepository.save(reader);
    }

    public void deleteById(Long readerId) {
        readerRepository.deleteById(readerId);
    }

    public Reader findById(Long readerId) throws ReaderNotFoundException {
        return readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new);
    }

    public List<Reader> findAll() {
        return readerRepository.findAll();
    }
}
