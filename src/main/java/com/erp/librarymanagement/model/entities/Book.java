package com.erp.librarymanagement.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Rajib Kumer Ghosh
 */

@Data // Lombok generates getters/setters
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "LMS_BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn_id", nullable = false)
    private IsbnCatalog isbnCatalog;


}
