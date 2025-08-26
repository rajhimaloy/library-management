package com.erp.librarymanagement.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

/**
 * @author Rajib Kumer Ghosh
 */

// Lombok generates getters/setters
/*@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder*/
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "LMS_LOAN")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id", nullable = false)
    private Borrower borrower;

    @Column(name = "borrower_at", nullable = false)
    private OffsetDateTime borrowedAt;

    @Column(name = "returned_at")
    private OffsetDateTime returnedAt; // If null, means open loan

    public Loan() {
    }

    public Loan(Long id, Book book, Borrower borrower, OffsetDateTime borrowedAt, OffsetDateTime returnedAt) {
        this.id = id;
        this.book = book;
        this.borrower = borrower;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public OffsetDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(OffsetDateTime borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public OffsetDateTime getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(OffsetDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }
}
