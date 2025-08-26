package com.erp.librarymanagement.model.dto;

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
public class BorrowerRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

}
