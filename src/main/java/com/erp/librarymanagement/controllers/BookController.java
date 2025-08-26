package com.erp.librarymanagement.controllers;
import com.erp.librarymanagement.model.dto.BookRequest;
import com.erp.librarymanagement.model.dto.BookResponse;
import com.erp.librarymanagement.model.entities.Book;
import com.erp.librarymanagement.services.iservices.IBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Author: Rajib Kumer Ghosh
 */

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private final IBookService iBookService;

    public BookController(IBookService iBookService) {
        this.iBookService = iBookService;
    }

    @GetMapping("/allbooks")
    public List<BookResponse> getAllBooks() {
        return iBookService.getAllBooks();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@Valid @RequestBody BookRequest req) {
        Book b = iBookService.registerBook(req);
        var res = new BookResponse();
        res.setId(b.getId());
        res.setIsbnNo(b.getIsbnCatalog().getIsbnNo());
        res.setTitle(b.getIsbnCatalog().getTitle());
        res.setAuthor(b.getIsbnCatalog().getAuthor());
        res.setAvailable(true);
        return res;
    }

}
