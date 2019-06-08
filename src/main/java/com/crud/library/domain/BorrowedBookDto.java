package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BorrowedBookDto {
    private Long id;
    private Long copyId;
    private Long readerId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowedBookDto(Long id, Long copyId, Long readerId, LocalDate borrowDate) {
        this.id = id;
        this.copyId = copyId;
        this.readerId = readerId;
        this.borrowDate = borrowDate;
    }
}
