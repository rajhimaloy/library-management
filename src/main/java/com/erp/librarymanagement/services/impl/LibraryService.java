package com.erp.librarymanagement.services.impl;

import com.erp.librarymanagement.exception.*;
import com.erp.librarymanagement.model.dto.*;
import com.erp.librarymanagement.model.entities.*;
import com.erp.librarymanagement.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import jakarta.transaction.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

@Service
@RequiredArgsConstructor
@Transactional
public class LibraryService {

    private final IBorrowerRepository borrowerRepo;
    private final IIsbnCatalogRepository catalogRepo;
    private final IBookRepository bookRepo;
    private final ILoanRepository loanRepo;

    public LibraryService(IBorrowerRepository b, IIsbnCatalogRepository c, IBookRepository br, ILoanRepository lr) {
        this.borrowerRepo = b; this.catalogRepo = c; this.bookRepo = br; this.loanRepo = lr;
    }

    // Borrower
    public Borrower registerBorrower(BorrowRequest req) {
        borrowerRepo.findByEmail(req.getEmail()).ifPresent(x -> {
            throw new ConflictException("Email already registered");
        });
        var b = new Borrower();
        b.setName(req.getName());
        b.setEmail(req.getEmail());
        return borrowerRepo.save(b);
    }

    // Book registration (new copy each call)
    @Transactional
    public Book registerBook(BookRequest req) {
        var catOpt = catalogRepo.findByIsbn(req.getIsbn());
        IsbnCatalog cat;
        if (catOpt.isPresent()) {
            cat = catOpt.get();
            if (!cat.getTitle().equals(req.getTitle()) || !cat.getAuthor().equals(req.getAuthor())) {
                throw new ConflictException("ISBN exists with different title/author");
            }
        } else {
            cat = new IsbnCatalog();
            cat.setIsbn(req.getIsbn());
            cat.setTitle(req.getTitle());
            cat.setAuthor(req.getAuthor());
            cat = catalogRepo.save(cat);
        }
        var book = new Book();
        book.setIsbnCatalog(cat);
        return bookRepo.save(book);
    }

    public List<BookResponse> listBooks() {
        return bookRepo.findAll().stream().map(b -> {
            boolean available = loanRepo.findByBookIdAndReturnedAtIsNull(b.getId()).isEmpty();
            var r = new BookResponse();
            r.setId(b.getId());
            r.setIsbn(b.getIsbnCatalog().getIsbn());
            r.setTitle(b.getIsbnCatalog().getTitle());
            r.setAuthor(b.getIsbnCatalog().getAuthor());
            r.setAvailable(available);
            return r;
        }).toList();
    }

    // Borrow and return
    @Transactional
    public Loan borrowBook(UUID borrowerId, Long bookId) {
        var borrower = borrowerRepo.findById(borrowerId)
                .orElseThrow(() -> new NotFoundException("Borrower not found"));
        var book = bookRepo.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        loanRepo.findByBookIdAndReturnedAtIsNull(bookId).ifPresent(x -> {
            throw new ConflictException("Book is already borrowed");
        });

        var loan = new Loan();
        loan.setBook(book);
        loan.setBorrower(borrower);
        loan.setBorrowedAt(OffsetDateTime.now());
        return loanRepo.save(loan);
    }

    @Transactional
    public Loan returnBook(UUID borrowerId, Long bookId) {
        var loan = loanRepo.findByBookIdAndReturnedAtIsNull(bookId)
                .orElseThrow(() -> new NotFoundException("No open loan for this book"));
        if (!loan.getBorrower().getId().equals(borrowerId)) {
            throw new ConflictException("This book is borrowed by another borrower");
        }
        loan.setReturnedAt(OffsetDateTime.now());
        return loanRepo.save(loan);
    }


}
