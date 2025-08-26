package com.erp.librarymanagement;

import com.erp.librarymanagement.exception.*;
import com.erp.librarymanagement.model.dto.*;
import com.erp.librarymanagement.model.entities.*;
import com.erp.librarymanagement.services.impl.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Author: Rajib Kumer Ghosh
 */

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoanServiceTests {

    @Autowired
    LoanService service;

    @Test
    void borrow_and_return_flow() {
        var borrower = service.registerBorrower(req("Alice","alice@example.com"));
        var b1 = service.registerBook(book("111","T","A"));

        Loan loan = service.borrowBook(borrower.getBorrowerId(), b1.getBookId());
        assertNotNull(loan.getLoanId());
        assertThrows(ConflictException.class, () -> service.borrowBook(borrower.getBorrowerId(), b1.getBookId()));

        service.returnBook(borrower.getBorrowerId(), b1.getBookId());
        assertDoesNotThrow(() -> service.borrowBook(borrower.getBorrowerId(), b1.getBookId()));
    }

    private BorrowerRequest req(String n, String e){ var r=new BorrowerRequest(); r.setName(n); r.setEmail(e); return r; }
    private BookRequest book(String i, String t, String a){ var r=new BookRequest(); r.setIsbnNo(i); r.setTitle(t); r.setAuthor(a); return r; }

}
