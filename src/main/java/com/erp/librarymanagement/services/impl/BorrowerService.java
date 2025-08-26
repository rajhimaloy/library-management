package com.erp.librarymanagement.services.impl;

import com.erp.librarymanagement.exception.ConflictException;
import com.erp.librarymanagement.model.dto.BorrowerRequest;
import com.erp.librarymanagement.model.entities.Borrower;
import com.erp.librarymanagement.repositories.IBorrowerRepository;
import com.erp.librarymanagement.services.iservices.IBorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

/*
 * Author: Rajib Kumer Ghosh
 */

@CacheConfig(cacheNames = "borrower")
@Service
public class BorrowerService implements IBorrowerService {

    @Autowired
    private final IBorrowerRepository iBorrowerRepository;

    public BorrowerService(IBorrowerRepository iBorrowerRepository) {
        this.iBorrowerRepository = iBorrowerRepository;
    }

    // Register Borrower
    @CachePut(value = "borrower", key = "#borrower.id")
    @Override
    public Borrower registerBorrower(BorrowerRequest req) {
        iBorrowerRepository.findByEmail(req.getEmail()).ifPresent(x -> {
            throw new ConflictException("Email already registered");
        });
        var b = new Borrower();
        b.setName(req.getName());
        b.setEmail(req.getEmail());
        return iBorrowerRepository.save(b);
    }
}
