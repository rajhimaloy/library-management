package com.erp.librarymanagement.controllers;
import com.erp.librarymanagement.model.dto.BookRequest;
import com.erp.librarymanagement.model.dto.BookResponse;
import com.erp.librarymanagement.model.entities.Book;
import com.erp.librarymanagement.services.impl.LibraryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Author: Rajib Kumer Ghosh
 */

@RestController
@RequestMapping("/books")
public class BookController {
    private final LibraryService service;
    public BookController(LibraryService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@Valid @RequestBody BookRequest req) {
        Book b = service.registerBook(req);
        var res = new BookResponse();
        res.setIsbnId(b.getIsbnId());
        res.setIsbnNo(b.getIsbnCatalog().getIsbnNo());
        res.setTitle(b.getIsbnCatalog().getTitle());
        res.setAuthor(b.getIsbnCatalog().getAuthor());
        res.setAvailable(true);
        return res;
    }

    @GetMapping
    public List<BookResponse> list() {
        return service.listBooks();
    }

}
