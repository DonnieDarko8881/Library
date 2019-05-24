package com.crud.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class BookDto {
    private long id;
    private String title;
    private String author;
    private int yearOfPublication;
    private List<CopyOfTheBookDto> copiesBookDtoInLibrary;

    public static class BookDtoBuilder {
        private long id;
        private String title;
        private String author;
        private int yearOfPublication;
        private List<CopyOfTheBookDto> copiesBookDtoInLibrary;


        public BookDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BookDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookDtoBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookDtoBuilder yearOfPublication(int yearOfPublication) {
            this.yearOfPublication = yearOfPublication;
            return this;
        }

        public BookDtoBuilder copiesBookInLibrary(List<CopyOfTheBookDto> copiesBookDtoInLibrary) {
            this.copiesBookDtoInLibrary = copiesBookDtoInLibrary;
            return this;
        }

        public BookDto build() {
            return new BookDto(id, title, author, yearOfPublication, copiesBookDtoInLibrary);
        }
    }

    private BookDto(long id, String title, String author, int yearOfPublication,
                    List<CopyOfTheBookDto> copiesBookDtoInLibrary) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.copiesBookDtoInLibrary = copiesBookDtoInLibrary;
    }
}
