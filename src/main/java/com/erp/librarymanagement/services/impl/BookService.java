package com.erp.librarymanagement.services.impl;

import com.erp.librarymanagement.exception.ConflictException;
import com.erp.librarymanagement.model.dto.BookDTO;
import com.erp.librarymanagement.model.entities.Book;
import com.erp.librarymanagement.model.entities.IsbnCatalog;
import com.erp.librarymanagement.model.mapper.DtoEntityMapper;
import com.erp.librarymanagement.repositories.IBookRepository;
import com.erp.librarymanagement.repositories.IIsbnCatalogRepository;
import com.erp.librarymanagement.repositories.ILoanRepository;
import com.erp.librarymanagement.services.iservices.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Author: Rajib Kumer Ghosh
 */

//@CacheConfig(cacheNames = "book")  //Transfer to Interface for best practice when relationship available
@Service
public class BookService implements IBookService {

    @Autowired
    private final IBookRepository iBookRepository;

    @Autowired
    private final IIsbnCatalogRepository iIsbnCatalogRepository;

    @Autowired
    private final ILoanRepository iLoanRepository;

    @Autowired
    private final DtoEntityMapper DtoEntityMapper;

    public BookService(IBookRepository iBookRepository, IIsbnCatalogRepository iIsbnCatalogRepository, ILoanRepository iLoanRepository, com.erp.librarymanagement.model.mapper.DtoEntityMapper dtoEntityMapper) {
        this.iBookRepository = iBookRepository;
        this.iIsbnCatalogRepository = iIsbnCatalogRepository;
        this.iLoanRepository = iLoanRepository;
        DtoEntityMapper = dtoEntityMapper;
    }

    // Retrieve all books and cache the result
    @Cacheable(value = "getallbooklist")
    @Override
    public List<BookDTO> getAllBookList() {
        return iBookRepository.findAll().stream().map(b -> {
            boolean available = iLoanRepository.findBookByIdAndReturnedAtIsNull(b.getId()).isEmpty();
            var r = new BookDTO();
            r.setId(b.getId());
            r.setIsbnNo(b.getIsbnCatalog().getIsbnNo());
            r.setTitle(b.getIsbnCatalog().getTitle());
            r.setAuthor(b.getIsbnCatalog().getAuthor());
            r.setAvailable(available);
            return r;
        }).toList();
    }

    // Book Registration
    //@Cacheable(value = "book", key = "#result.id")  //Transfer to Interface
    @Transactional
    @Override
    public BookDTO bookRegistration(BookDTO bookDTO) {
        var catOpt = iIsbnCatalogRepository.findByIsbnNo(bookDTO.getIsbnNo());
        IsbnCatalog isbnCatalog;
        if (catOpt.isPresent()) {
            isbnCatalog = catOpt.get();
            if (!isbnCatalog.getTitle().equals(bookDTO.getTitle()) || !isbnCatalog.getAuthor().equals(bookDTO.getAuthor())) {
                throw new ConflictException("ISBN exists with different title and author");
            }
        } else {
            isbnCatalog = new IsbnCatalog();
            isbnCatalog.setIsbnNo(bookDTO.getIsbnNo());
            isbnCatalog.setTitle(bookDTO.getTitle());
            isbnCatalog.setAuthor(bookDTO.getAuthor());
            isbnCatalog = iIsbnCatalogRepository.save(isbnCatalog);
        }
        var book = new Book();
        book.setIsbnCatalog(isbnCatalog);
        Book bookRes = iBookRepository.save(book);
        return DtoEntityMapper.toBookDTO(bookRes);
    }

}
