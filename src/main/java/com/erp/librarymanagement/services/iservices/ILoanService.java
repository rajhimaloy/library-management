package com.erp.librarymanagement.services.iservices;

import com.erp.librarymanagement.model.entities.Loan;

/*
 * Author: Rajib Kumer Ghosh
 */

public interface ILoanService {

    public Loan borrowBook(Long borrowerId, Long bookId);
    public Loan returnBook(Long borrowerId, Long bookId);
}
