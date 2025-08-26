package com.erp.librarymanagement.repositories;

import com.erp.librarymanagement.model.entities.IsbnCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Author: Rajib Kumer Ghosh
 */

@Repository
public interface IIsbnCatalogRepository extends JpaRepository<IsbnCatalog, Long> {
    Optional<IsbnCatalog> findByIsbnNo(String isbnNo);
}
