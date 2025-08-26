package com.erp.librarymanagement.services.impl;

import com.erp.librarymanagement.exception.*;
import com.erp.librarymanagement.model.entities.*;
import com.erp.librarymanagement.repositories.*;
import com.erp.librarymanagement.services.iservices.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import jakarta.transaction.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

@Service
//@RequiredArgsConstructor
@Transactional
public class LoanService implements ILoanService {

    @Autowired
    private final IBorrowerRepository iBorrowerRepository;

    @Autowired
    private final IBookRepository iBookRepository;

    @Autowired
    private final ILoanRepository iLoanRepository;

    public LoanService(IBorrowerRepository iBorrowerRepository, IBookRepository iBookRepository, ILoanRepository iLoanRepository) {
        this.iBorrowerRepository = iBorrowerRepository;
        this.iBookRepository = iBookRepository;
        this.iLoanRepository = iLoanRepository;
    }

    // Borrow and return
    @Transactional
    public Loan borrowBook(UUID borrowerId, Long bookId) {
        var borrower = iBorrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new NotFoundException("Borrower not found"));
        var book = iBookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        iLoanRepository.findByBookIdAndReturnedAtIsNull(bookId).ifPresent(x -> {
            throw new ConflictException("Book is already borrowed");
        });

        var loan = new Loan();
        loan.setBook(book);
        loan.setBorrower(borrower);
        loan.setBorrowedAt(OffsetDateTime.now());
        return iLoanRepository.save(loan);
    }

    @Transactional
    public Loan returnBook(UUID borrowerId, Long bookId) {
        var loan = iLoanRepository.findByBookIdAndReturnedAtIsNull(bookId)
                .orElseThrow(() -> new NotFoundException("No open loan for this book"));
        if (!loan.getBorrower().getId().equals(borrowerId)) {
            throw new ConflictException("This book is borrowed by another borrower");
        }
        loan.setReturnedAt(OffsetDateTime.now());
        return iLoanRepository.save(loan);
    }


}
