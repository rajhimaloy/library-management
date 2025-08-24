package com.erp.librarymanagement.repositories;
import com.erp.librarymanagement.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
}
