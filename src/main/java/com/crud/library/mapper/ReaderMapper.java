package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {
    public ReaderDto maptoReaderDto(final Reader reader) {
        return new ReaderDto(reader.getId(), reader.getFirstName(), reader.getLastName(), reader.getDateOfRegister());
    }

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(readerDto.getFirstName(), readerDto.getLastName());
    }

    public List<ReaderDto> maptoReaderDtoList(final List<Reader> readers) {
        return readers.stream().
                map(reader -> new ReaderDto(reader.getId(),
                        reader.getFirstName(),
                        reader.getLastName(),
                        reader.getDateOfRegister()))
                .collect(Collectors.toList());
    }
}
