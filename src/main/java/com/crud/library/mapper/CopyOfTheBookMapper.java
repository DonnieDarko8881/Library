package com.crud.library.mapper;

import com.crud.library.domain.CopyOfTheBook;
import com.crud.library.domain.CopyOfTheBookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyOfTheBookMapper {

    public CopyOfTheBookDto mapTocopyOfTheBookDto(final CopyOfTheBook copyOfTheBook) {
        return new CopyOfTheBookDto(copyOfTheBook.getId(), copyOfTheBook.getBook().getId(), copyOfTheBook.getStatus());
    }

    public List<CopyOfTheBookDto> mapToCopyOfTheBookDtoList(final List<CopyOfTheBook> copyOfTheBooks) {
        return copyOfTheBooks.stream()
                .map(copy -> new CopyOfTheBookDto(
                        copy.getId(),
                        copy.getBook().getId(),
                        copy.getStatus()))
                .collect(Collectors.toList());
    }
}
