package com.erp.librarymanagement.controllers;

import com.erp.librarymanagement.model.dto.BorrowRequest;
import com.erp.librarymanagement.model.dto.BorrowerResponse;
import com.erp.librarymanagement.model.entities.Borrower;
import com.erp.librarymanagement.services.impl.LibraryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/*
 * Author: Rajib Kumer Ghosh
 */

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {

    private final LibraryService service;
    public BorrowerController(LibraryService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BorrowerResponse create(@Valid @RequestBody BorrowRequest req) {
        Borrower b = service.registerBorrower(req);
        var res = new BorrowerResponse();
        res.setBorrowerId(b.getBorrowerId());
        res.setName(b.getName());
        res.setEmail(b.getEmail());
        return res;
    }

}
