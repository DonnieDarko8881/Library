package com.crud.library.repository;

import com.crud.library.domain.CopyOfTheBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepository extends JpaRepository<CopyOfTheBook,Long> {
}
