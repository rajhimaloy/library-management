package com.erp.librarymanagement;

import com.erp.librarymanagement.exception.*;
import com.erp.librarymanagement.model.dto.*;
import com.erp.librarymanagement.model.entities.*;
import com.erp.librarymanagement.services.impl.LoanService;
import com.erp.librarymanagement.services.iservices.IBookService;
import com.erp.librarymanagement.services.iservices.IBorrowerService;
import com.erp.librarymanagement.services.iservices.ILoanService;
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
    ILoanService iLoanService;

    @Autowired
    IBorrowerService iBorrowerService;

    @Autowired
    IBookService iBookService;

    @Test
    void borrow_and_return_flow() {
        var borrower = iBorrowerService.borrowerRegistration(req("Rajib","rajib@localhost.com"));
        var b1 = iBookService.bookRegistration(book("10001","Java SE","Rajib Kumer Ghosh"));

        Loan loan = iLoanService.borrowBook(borrower.getId(), b1.getId());
        assertNotNull(loan.getId());
        assertThrows(ConflictException.class, () -> iLoanService.borrowBook(borrower.getId(), b1.getId()));

        iLoanService.returnBook(borrower.getId(), b1.getId());
        assertDoesNotThrow(() -> iLoanService.borrowBook(borrower.getId(), b1.getId()));
    }

    private BorrowerDTO req(String n, String e){ var r=new BorrowerDTO(); r.setName(n); r.setEmail(e); return r; }
    private BookDTO book(String i, String t, String a){ var r=new BookDTO(); r.setIsbnNo(i); r.setTitle(t); r.setAuthor(a); return r; }

}
