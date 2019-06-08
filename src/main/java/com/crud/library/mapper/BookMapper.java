package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    private final BookCopyMapper bookCopyMapper;

    @Autowired
    public BookMapper(BookCopyMapper bookCopyMapper) {
        this.bookCopyMapper = bookCopyMapper;
    }

    public Book mapToBook(final BookDto bookDto) {
        return Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publicationYear(bookDto.getYearOfPublication())
                .build();
    }

    public BookDto mapToBookDto(final Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .yearOfPublication(book.getPublicationYear())
                .copiesBookDtoInLibrary(bookCopyMapper.mapToCopyOfTheBookDtoList(book.getCopiesBookInLibrary()))
                .build();
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(book -> BookDto.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .yearOfPublication(book.getPublicationYear())
                        .copiesBookDtoInLibrary(bookCopyMapper.mapToCopyOfTheBookDtoList(book.getCopiesBookInLibrary()))
                        .build())
                .collect(Collectors.toList());
    }
}
