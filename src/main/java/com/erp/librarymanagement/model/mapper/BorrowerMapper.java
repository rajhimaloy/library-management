package com.erp.librarymanagement.model.mapper;

import com.erp.librarymanagement.model.dto.BookRequest;
import com.erp.librarymanagement.model.dto.BookResponse;
import com.erp.librarymanagement.model.dto.BorrowerRequest;
import com.erp.librarymanagement.model.entities.Book;
import com.erp.librarymanagement.model.entities.Borrower;
import org.mapstruct.*;

import java.util.List;

/*
 * Author: Rajib Kumer Ghosh
 *
 * componentModel = "spring" ensures Spring injects the mapper as a bean (@Autowired or constructor).
 * List conversions are declared explicitly, allowing seamless mapping of collections.
 * You can add @Mapping annotations if field names differ between DTO and Entity (not needed now).
 * nullValuePropertyMappingStrategy prevents overwriting existing entity fields with null values from the DTO.
 */

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BorrowerMapper {

    // --- Borrower ---
    Borrower toEntity(BorrowerRequest borrowerRequest); // DTO → Entity
    void updateEntityFromDto(BorrowerRequest dto, @MappingTarget Borrower entity);
    BorrowerRequest toBorrowerRequest(Borrower Borrower);
    Borrower toBorrower(BorrowerRequest request);
    List<BorrowerRequest> toBorrowerRequests(List<Borrower> Borrowers);

    
    // --- Book ---
    BookResponse toBookDTO(Book Book);
    Book toBook(BookResponse dto);
    List<BookResponse> toBookDTOs(List<Book> Books);
    List<Book> toBooks(List<BookResponse> dtos);

}
