package com.erp.librarymanagement.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 * Author: Rajib Kumer Ghosh
 */

/*@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor*/
public class BookDTO implements Serializable {

    private Long id;

    @NotBlank
    private String isbnNo;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private boolean available;

    public BookDTO() {
    }

    public BookDTO(Long id, String isbnNo, String title, String author, boolean available) {
        this.id = id;
        this.isbnNo = isbnNo;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbnNo() {
        return isbnNo;
    }

    public void setIsbnNo(String isbnNo) {
        this.isbnNo = isbnNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
