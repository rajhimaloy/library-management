package com.erp.librarymanagement.controllers;
import com.erp.librarymanagement.model.entities.Loan;
import com.erp.librarymanagement.services.impl.LoanService;
import com.erp.librarymanagement.services.iservices.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private final ILoanService iLoanService;

    public LoanController(ILoanService iLoanService) {
        this.iLoanService = iLoanService;
    }

    @PostMapping("/{borrowerId}/borrowbook/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> borrowBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        Loan loan = iLoanService.borrowBook(borrowerId, bookId);
        return Map.of(
                "id", loan.getId(),
                "borrowerId", loan.getBorrower().getId(),
                "bookId", loan.getBook().getId(),
                "borrowedAt", loan.getBorrowedAt()
        );
    }

    @PostMapping("/{borrowerId}/returnbook/{bookId}")
    public Map<String, Object> returnBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        Loan loan = iLoanService.returnBook(borrowerId, bookId);
        return Map.of(
                "id", loan.getId(),
                "returnedAt", loan.getReturnedAt()
        );
    }

}
