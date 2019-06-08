package com.crud.library.mapper;

import com.crud.library.domain.BorrowedBook;
import com.crud.library.domain.BorrowedBookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowedBookMapper {

    public BorrowedBookDto mapToBorrowedBookDto(final BorrowedBook borrowedBook) {
        return new BorrowedBookDto(borrowedBook.getId(), borrowedBook.getCopyOfTheBook().getId(),
                borrowedBook.getReader().getId(), borrowedBook.getBorrowDate(), borrowedBook.getReturnDate());
    }

    public List<BorrowedBookDto> mapToBorrowedBookDtoList(final List<BorrowedBook> borrowedBooks) {
        return borrowedBooks.stream()
                .map(borrowedBook -> new BorrowedBookDto(
                        borrowedBook.getId(),
                        borrowedBook.getCopyOfTheBook().getId(),
                        borrowedBook.getReader().getId(),
                        borrowedBook.getBorrowDate(),
                        borrowedBook.getReturnDate()))
                .collect(Collectors.toList());
    }
}
