package com.erp.librarymanagement.services.iservices;

import com.erp.librarymanagement.model.dto.BookRequest;
import com.erp.librarymanagement.model.dto.BookResponse;
import com.erp.librarymanagement.model.entities.Book;

import java.util.List;

public interface IBookService {

    public List<BookResponse> getAllBooks();
    public Book registerBook(BookRequest req);
}
