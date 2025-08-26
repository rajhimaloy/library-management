package com.erp.librarymanagement.model.dto;

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
public class BookResponse {
    private Long id;
    private String isbnNo;
    private String title;
    private String author;
    private boolean available;

}
