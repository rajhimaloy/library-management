package com.erp.librarymanagement.controllers;

import com.erp.librarymanagement.model.dto.BorrowerRequest;
import com.erp.librarymanagement.model.dto.BorrowerResponse;
import com.erp.librarymanagement.model.entities.Borrower;
import com.erp.librarymanagement.services.iservices.IBorrowerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/*
 * Author: Rajib Kumer Ghosh
 */

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    @Autowired
    private final IBorrowerService iBorrowerService;

    public BorrowerController(IBorrowerService iBorrowerService) {
        this.iBorrowerService = iBorrowerService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public BorrowerResponse create(@Valid @RequestBody BorrowerRequest req) {
        Borrower b = iBorrowerService.registerBorrower(req);
        var res = new BorrowerResponse();
        res.setId(b.getId());
        res.setName(b.getName());
        res.setEmail(b.getEmail());
        return res;
    }

}
