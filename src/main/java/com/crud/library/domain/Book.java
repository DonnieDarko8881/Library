package com.crud.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "year_of_publication")
    private int yearOfPublication;

    @OneToMany(
            targetEntity = CopyOfTheBook.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<CopyOfTheBook> copiesBookInLibrary = new ArrayList<>();

    public static class BookBuilder {
        private long id;
        private String title;
        private String author;
        private int yearOfPublication;
        private List<CopyOfTheBook> copiesBookInLibrary;


        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder yearOfPublication(int yearOfPublication) {
            this.yearOfPublication = yearOfPublication;
            return this;
        }

        public BookBuilder copiesBookInLibrary(List<CopyOfTheBook> copiesBookInLibrary) {
            this.copiesBookInLibrary = copiesBookInLibrary;
            return this;
        }

        public Book build() {
            return new Book(title, author, yearOfPublication, copiesBookInLibrary);
        }
    }

    private Book(String title, String author, int yearOfPublication, List<CopyOfTheBook> copiesBookInLibrary) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.copiesBookInLibrary = copiesBookInLibrary;
    }
}

