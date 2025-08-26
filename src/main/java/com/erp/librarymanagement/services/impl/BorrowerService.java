package com.erp.librarymanagement.services.impl;

import com.erp.librarymanagement.exception.ConflictException;
import com.erp.librarymanagement.model.dto.BorrowerDTO;
import com.erp.librarymanagement.model.entities.Borrower;
import com.erp.librarymanagement.model.mapper.DtoEntityMapper;
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

    //private final ModelMapper modelMapper;
    @Autowired
    private final DtoEntityMapper DtoEntityMapper;

    public BorrowerService(IBorrowerRepository iBorrowerRepository, DtoEntityMapper DtoEntityMapper) {
        this.iBorrowerRepository = iBorrowerRepository;
        this.DtoEntityMapper = DtoEntityMapper;
    }

    // Register Borrower
    @CachePut(value = "borrower", key = "#borrower.id")
    @Override
    public BorrowerDTO borrowerRegistration(BorrowerDTO borrowerDTO) {
        iBorrowerRepository.findByEmail(borrowerDTO.getEmail()).ifPresent(x -> {
            throw new ConflictException("Email already registered");
        });

        //Borrower borrower = modelMapper.map(borrowerDTO, Borrower.class);
        Borrower borrower = DtoEntityMapper.toBorrower(borrowerDTO);

        Borrower borrowerRes = iBorrowerRepository.save(borrower);
        return DtoEntityMapper.toBorrowerDTO(borrowerRes);
    }
}
