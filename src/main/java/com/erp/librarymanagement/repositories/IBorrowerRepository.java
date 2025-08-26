package com.erp.librarymanagement.repositories;

import com.erp.librarymanagement.model.entities.Borrower;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

@Repository
public interface IBorrowerRepository extends JpaRepository<Borrower, Long> {
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Borrower> findByEmail(String email);

    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Borrower> findById(Long id);

}
