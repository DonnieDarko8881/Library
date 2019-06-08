package com.crud.library.mapper;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookCopyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {

    public BookCopyDto mapToCopyOfTheBookDto(final BookCopy copyOfTheBook) {
        return new BookCopyDto(copyOfTheBook.getId(), copyOfTheBook.getBook().getId(), copyOfTheBook.getStatus());
    }

    public List<BookCopyDto> mapToCopyOfTheBookDtoList(final List<BookCopy> copyOfTheBooks) {
        return copyOfTheBooks.stream()
                .map(copy -> new BookCopyDto(
                        copy.getId(),
                        copy.getBook().getId(),
                        copy.getStatus()))
                .collect(Collectors.toList());
    }
}
