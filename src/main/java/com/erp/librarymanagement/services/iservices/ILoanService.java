package com.erp.librarymanagement.services.iservices;

import com.erp.librarymanagement.model.entities.Loan;

import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

public interface ILoanService {

    public Loan borrowBook(UUID borrowerId, Long bookId);
    public Loan returnBook(UUID borrowerId, Long bookId);
}
