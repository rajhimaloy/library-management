package com.erp.librarymanagement.services.iservices;

import com.erp.librarymanagement.model.dto.BookDTO;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

public interface IBookService {

    public List<BookDTO> getAllBookList();

    @CachePut(value = "book", key = "#book.id")
    public BookDTO bookRegistration(BookDTO bookDTO);
}
