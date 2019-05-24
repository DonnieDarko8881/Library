package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    private CopyOfTheBookMapper copyOfTheBookMapper;

    @Autowired
    public BookMapper(CopyOfTheBookMapper copyOfTheBookMapper) {
        this.copyOfTheBookMapper = copyOfTheBookMapper;
    }

    public Book mapToBook(final BookDto bookDto) {
        return new Book.BookBuilder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .yearOfPublication(bookDto.getYearOfPublication())
                .build();
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto.BookDtoBuilder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .yearOfPublication(book.getYearOfPublication())
                .copiesBookInLibrary(copyOfTheBookMapper.mapToCopyOfTheBookDtoList(book.getCopiesBookInLibrary()))
                .build();
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(book -> new BookDto.BookDtoBuilder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .yearOfPublication(book.getYearOfPublication())
                        .copiesBookInLibrary(copyOfTheBookMapper.mapToCopyOfTheBookDtoList(book.getCopiesBookInLibrary()))
                        .build())
                .collect(Collectors.toList());
    }


}
