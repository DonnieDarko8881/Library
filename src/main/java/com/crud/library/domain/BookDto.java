package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BookDto {
    private long id;
    private String title;
    private String author;
    private int yearOfPublication;
    private List<BookCopyDto> copiesBookDtoInLibrary;
}
