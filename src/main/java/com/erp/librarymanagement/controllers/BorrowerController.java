package com.erp.librarymanagement.controllers;

import com.erp.librarymanagement.model.dto.BorrowerDTO;
import com.erp.librarymanagement.services.iservices.IBorrowerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*POST http://localhost:8080/api/rest/lms/borrower/registration
    {
        "name": "Rajib Kumer Ghosh",
        "age": 35
    }*/
    @PostMapping("/registration")
    public ResponseEntity<BorrowerDTO> borrowerRegistration(@Valid @RequestBody BorrowerDTO borrowerDTO) {
        //log.info("Request to create borrower: {}", borrowerDTO);
        return new ResponseEntity<>(iBorrowerService.borrowerRegistration(borrowerDTO), HttpStatus.CREATED); // Returns a 201 Created response
    }

}
