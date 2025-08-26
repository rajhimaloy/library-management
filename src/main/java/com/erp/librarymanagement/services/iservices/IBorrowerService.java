package com.erp.librarymanagement.services.iservices;

import com.erp.librarymanagement.model.dto.BorrowerDTO;
import com.erp.librarymanagement.model.entities.Borrower;

public interface IBorrowerService {

    public BorrowerDTO borrowerRegistration(BorrowerDTO borrowerDTO);
}
