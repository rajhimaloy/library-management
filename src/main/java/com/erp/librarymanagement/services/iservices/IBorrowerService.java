package com.erp.librarymanagement.services.iservices;

import com.erp.librarymanagement.model.dto.BookDTO;
import com.erp.librarymanagement.model.dto.BorrowerDTO;
import com.erp.librarymanagement.model.entities.Borrower;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

public interface IBorrowerService {

    public List<BorrowerDTO> getBorrowerList();

    @CachePut(value = "borrower", key = "#borrower.id")
    public BorrowerDTO borrowerRegistration(BorrowerDTO borrowerDTO);
    public BorrowerDTO updateBorrower(Long id, BorrowerDTO borrowerDTO);
}
