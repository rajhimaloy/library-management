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

@Data // Lombok generates getters/setters
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
