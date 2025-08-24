package com.erp.librarymanagement.controllers;
import com.erp.librarymanagement.model.entities.Loan;
import com.erp.librarymanagement.services.impl.LibraryService;
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

    private final LibraryService service;
    public LoanController(LibraryService service) { this.service = service; }

    @PostMapping("/{borrowerId}/borrow/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> borrow(@PathVariable UUID borrowerId, @PathVariable Long bookId) {
        Loan loan = service.borrowBook(borrowerId, bookId);
        return Map.of(
                "loanId", loan.getId(),
                "borrowerId", loan.getBorrower().getId(),
                "bookId", loan.getBook().getId(),
                "borrowedAt", loan.getBorrowedAt()
        );
    }

    @PostMapping("/{borrowerId}/return/{bookId}")
    public Map<String, Object> returnBook(@PathVariable UUID borrowerId, @PathVariable Long bookId) {
        Loan loan = service.returnBook(borrowerId, bookId);
        return Map.of(
                "loanId", loan.getId(),
                "returnedAt", loan.getReturnedAt()
        );
    }

}
