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

// Lombok generates getters/setters
/*@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder*/
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

    public Book() {
    }

    public Book(Long id, IsbnCatalog isbnCatalog) {
        this.id = id;
        this.isbnCatalog = isbnCatalog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IsbnCatalog getIsbnCatalog() {
        return isbnCatalog;
    }

    public void setIsbnCatalog(IsbnCatalog isbnCatalog) {
        this.isbnCatalog = isbnCatalog;
    }
}
