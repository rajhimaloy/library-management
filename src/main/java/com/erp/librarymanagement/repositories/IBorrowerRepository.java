package com.erp.librarymanagement.repositories;

import com.erp.librarymanagement.model.entities.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

@Repository
public interface IBorrowerRepository extends JpaRepository<Borrower, Long> {
    Optional<Borrower> findByEmail(String email);

}
