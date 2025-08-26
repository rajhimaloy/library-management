package com.erp.librarymanagement.model.dto;

import com.erp.librarymanagement.model.enums.BorrowerStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Author: Rajib Kumer Ghosh
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerDTO {

    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    private BorrowerStatus status;

    public BorrowerDTO() {
    }

    public BorrowerDTO(Long id, String name, String email, BorrowerStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }

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
