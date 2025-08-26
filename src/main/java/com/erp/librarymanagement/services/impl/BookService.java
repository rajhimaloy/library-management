package com.erp.librarymanagement.services.impl;

import com.erp.librarymanagement.exception.ConflictException;
import com.erp.librarymanagement.model.dto.BookRequest;
import com.erp.librarymanagement.model.dto.BookResponse;
import com.erp.librarymanagement.model.entities.Book;
import com.erp.librarymanagement.model.entities.IsbnCatalog;
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

@CacheConfig(cacheNames = "book")
@Service
public class BookService implements IBookService {

    @Autowired
    private final IBookRepository iBookRepository;

    @Autowired
    private final IIsbnCatalogRepository iIsbnCatalogRepository;

    @Autowired
    private final ILoanRepository iLoanRepository;

    public BookService(IBookRepository iBookRepository, IIsbnCatalogRepository iIsbnCatalogRepository, ILoanRepository iLoanRepository) {
        this.iBookRepository = iBookRepository;
        this.iIsbnCatalogRepository = iIsbnCatalogRepository;
        this.iLoanRepository = iLoanRepository;
    }

    // Retrieve all books and cache the result
    @Cacheable(value = "getallbooks")
    @Override
    public List<BookResponse> getAllBooks() {
        return iBookRepository.findAll().stream().map(b -> {
            boolean available = iLoanRepository.findByBookIdAndReturnedAtIsNull(b.getId()).isEmpty();
            var r = new BookResponse();
            r.setId(b.getId());
            r.setIsbnNo(b.getIsbnCatalog().getIsbnNo());
            r.setTitle(b.getIsbnCatalog().getTitle());
            r.setAuthor(b.getIsbnCatalog().getAuthor());
            r.setAvailable(available);
            return r;
        }).toList();
    }

    // Book Registration
    @CachePut(value = "book", key = "#book.id")
    @Transactional
    @Override
    public Book registerBook(BookRequest req) {
        var catOpt = iIsbnCatalogRepository.findByIsbn(req.getIsbnNo());
        IsbnCatalog cat;
        if (catOpt.isPresent()) {
            cat = catOpt.get();
            if (!cat.getTitle().equals(req.getTitle()) || !cat.getAuthor().equals(req.getAuthor())) {
                throw new ConflictException("ISBN exists with different title/author");
            }
        } else {
            cat = new IsbnCatalog();
            cat.setIsbnNo(req.getIsbnNo());
            cat.setTitle(req.getTitle());
            cat.setAuthor(req.getAuthor());
            cat = iIsbnCatalogRepository.save(cat);
        }
        var book = new Book();
        book.setIsbnCatalog(cat);
        return iBookRepository.save(book);
    }
}
