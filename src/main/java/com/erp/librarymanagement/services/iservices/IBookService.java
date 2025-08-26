package com.erp.librarymanagement.services.iservices;

import com.erp.librarymanagement.model.dto.BookDTO;

import java.util.List;

public interface IBookService {

    public List<BookDTO> getAllBookList();

    public BookDTO bookRegistration(BookDTO bookDTO);
}
