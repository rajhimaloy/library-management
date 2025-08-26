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
@Table(name = "LMS_ISBN_CATALOG")
public class IsbnCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isbn_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "isbn_no", nullable = false, length = 50)
    private String isbnNo;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "author", nullable = false, length = 150)
    private String author;

}
