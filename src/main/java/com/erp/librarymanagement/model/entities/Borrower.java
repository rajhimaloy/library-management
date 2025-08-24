package com.erp.librarymanagement.model.entities;

import com.erp.librarymanagement.model.enums.BorrowerStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

/**
 * @author Rajib Kumer Ghosh
 */

@Data // Lombok generates getters/setters
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "LMS_BORROWER")
public class Borrower extends Auditable {

    @Id
    @GeneratedValue
    @Column(name = "borrower_id", updatable = false, nullable = false)
    private UUID borrowerId;

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
}
