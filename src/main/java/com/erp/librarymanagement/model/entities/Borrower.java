package com.erp.librarymanagement.model.entities;

import com.erp.librarymanagement.model.enums.BorrowerStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "LMS_BORROWER")
public class Borrower extends Auditable {

    @Id
    @GeneratedValue
    @Column(name = "borrower_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BorrowerStatus status;

    public Borrower() {
    }

    public Borrower(Long id, String name, String email, BorrowerStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BorrowerStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowerStatus status) {
        this.status = status;
    }
}
