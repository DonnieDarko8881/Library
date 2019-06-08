package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedNativeQuery(
        name = "BookCopy.retrieveAmountAccessBookToBorrow",

        query = "SELECT * FROM book_copy " +
                "WHERE book_id = :bookId " +
                "AND is_borrowed = false " +
                "AND status = 'GOOD'",
        resultClass = BookCopy.class
)

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "book_copy")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "status")
    private String status;

    @Column(name = "is_borrowed")
    private Boolean borrowed;

    public BookCopy(Book book, String status) {
        this.book = book;
        this.status = status;
        this.borrowed = false;
    }

    public Boolean isBorrowed() {
        return borrowed;
    }
}

