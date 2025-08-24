package com.erp.librarymanagement.repositories;
import com.erp.librarymanagement.model.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByBookIdAndReturnedAtIsNull(Long bookId);
}
