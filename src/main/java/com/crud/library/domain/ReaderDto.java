package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReaderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate registerDate;
}
