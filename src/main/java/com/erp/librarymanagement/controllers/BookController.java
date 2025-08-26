package com.erp.librarymanagement.controllers;
import com.erp.librarymanagement.model.dto.BookDTO;
import com.erp.librarymanagement.services.iservices.IBookService;
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
@RequestMapping("/book")
public class BookController {

    @Autowired
    private final IBookService iBookService;

    public BookController(IBookService iBookService) {
        this.iBookService = iBookService;
    }


    /*GET http://localhost:8080/api/rest/lms/book/getallbooklist*/
    @GetMapping("/getallbooklist")
    public List<BookDTO> getAllBookList() throws Exception {
        return iBookService.getAllBookList();
    }


    /*POST http://localhost:8080/api/rest/lms/book/registration
    {
        "name": "Rajib Kumer Ghosh",
        "age": 35
    }*/
    @PostMapping("/registration")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDTO> bookRegistration(@Valid @RequestBody BookDTO bookDTO) {
        //log.info("Registration of the book: {}", bookDTO);
        return new ResponseEntity<>(iBookService.bookRegistration(bookDTO), HttpStatus.CREATED); // Returns a 201 Created response
    }

}
