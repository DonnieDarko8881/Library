package com.crud.library.service;

import com.crud.library.Exception.ReaderNotFoundException;
import com.crud.library.domain.Reader;
import com.crud.library.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReaderService {

    private ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public void saveReader(Reader reader) {
        readerRepository.save(reader);
    }

    public void deleteReaderById(Long readerId) {
        readerRepository.deleteById(readerId);
    }

    public Reader findReaderById(Long readerId) throws ReaderNotFoundException {
        return readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new);
    }

    public List<Reader> getReaders() {
        return readerRepository.findAll();
    }

}
