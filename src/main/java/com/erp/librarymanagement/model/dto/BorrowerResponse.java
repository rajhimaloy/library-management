package com.erp.librarymanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerResponse {

    private UUID borrowerId;
    private String name;
    private String email;

}
