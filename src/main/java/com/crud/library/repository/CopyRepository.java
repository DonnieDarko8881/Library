package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopyRepository extends JpaRepository<BookCopy, Long> {
    List<BookCopy> retrieveAmountAccessBookToBorrow(@Param("bookId") Long bookId);
}
