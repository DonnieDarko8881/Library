package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "copy_of_book")
public class CopyOfTheBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "status")
    private String status;

    @Column(name = "is_borrowed")
    private Boolean borrowed;

    public CopyOfTheBook(Book book, String status) {
        this.book = book;
        this.status = status;
        this.borrowed = false;
    }
}
