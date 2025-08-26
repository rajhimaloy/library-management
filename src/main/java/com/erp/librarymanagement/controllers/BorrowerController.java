package com.erp.librarymanagement.controllers;

import com.erp.librarymanagement.model.dto.BorrowerDTO;
import com.erp.librarymanagement.services.iservices.IBorrowerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*Data JPA CRUD*/

    /*GET http://localhost:8080/api/rest/lms/borrower/getborrowerlist*/
    @GetMapping("/getborrowerlist")
    public List<BorrowerDTO> getBorrowerList() throws Exception {
        return iBorrowerService.getBorrowerList();
    }

    /*POST http://localhost:8080/api/rest/lms/borrower/registration
    {
        "id": 1,
        "name": "Rajib Kumer Ghosh",
        "email": "rajib@localhost.com",
        "status": "ACTIVE"
    }*/
    @PostMapping("/registration")
    public ResponseEntity<BorrowerDTO> borrowerRegistration(@Valid @RequestBody BorrowerDTO borrowerDTO) {
        //log.info("Request to create borrower: {}", borrowerDTO);
        return new ResponseEntity<>(iBorrowerService.borrowerRegistration(borrowerDTO), HttpStatus.CREATED); // Returns a 201 Created response
    }

}
