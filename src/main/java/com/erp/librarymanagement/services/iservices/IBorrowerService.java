package com.erp.librarymanagement.services.iservices;

import com.erp.librarymanagement.model.dto.BorrowerRequest;
import com.erp.librarymanagement.model.entities.Borrower;

public interface IBorrowerService {

    public Borrower registerBorrower(BorrowerRequest req);
}
