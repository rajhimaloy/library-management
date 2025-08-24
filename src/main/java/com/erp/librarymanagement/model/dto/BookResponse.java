package com.erp.librarymanagement.model.dto;

/*
 * Author: Rajib Kumer Ghosh
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long isbnId;
    private String isbnNo;
    private String title;
    private String author;
    private boolean available;

}
