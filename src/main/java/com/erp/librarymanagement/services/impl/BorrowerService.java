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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Author: Rajib Kumer Ghosh
 */

//@CacheConfig(cacheNames = "borrower")
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

    // Retrieve all books and cache the result
    @Cacheable(value = "getborrowerlist")
    @Override
    public List<BorrowerDTO> getBorrowerList() {
        List<Borrower> borrowerList = iBorrowerRepository.findAll();
        return borrowerList.stream()
                .map(DtoEntityMapper::toBorrowerDTO)
                .collect(Collectors.toList());
    }

    // Register Borrower
    //@CachePut(value = "borrower", key = "#borrower.id")
    @Transactional
    @Override
    public BorrowerDTO borrowerRegistration(BorrowerDTO borrowerDTO) {
        iBorrowerRepository.findByEmail(borrowerDTO.getEmail()).ifPresent(x -> {
            throw new ConflictException("Email already registered");
        });

        Borrower borrower = DtoEntityMapper.toBorrower(borrowerDTO);

        Borrower savedBorrower = iBorrowerRepository.save(borrower);

        return DtoEntityMapper.toBorrowerDTO(savedBorrower);
    }

    @Transactional
    @Override
    public BorrowerDTO updateBorrower(Long id, BorrowerDTO borrowerDTO) {
        // Fetch the existing borrower with its current version
        Borrower existingBorrower = iBorrowerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrower not found")); // Use a proper exception here

        // Update the properties
        existingBorrower.setName(borrowerDTO.getName());
        existingBorrower.setEmail(borrowerDTO.getEmail());
        existingBorrower.setStatus(borrowerDTO.getStatus());

        // Save the updated entity. Hibernate uses the existing version to perform the update.
        Borrower updatedBorrower = iBorrowerRepository.save(existingBorrower);

        return DtoEntityMapper.toBorrowerDTO(updatedBorrower);
    }
}
