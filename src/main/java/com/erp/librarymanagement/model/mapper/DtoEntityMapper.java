package com.erp.librarymanagement.model.mapper;

import com.erp.librarymanagement.model.dto.BookDTO;
import com.erp.librarymanagement.model.dto.BorrowerDTO;
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
public interface DtoEntityMapper {

    // --- Borrower ---
    Borrower toEntity(BorrowerDTO borrowerDTO); // DTO → Entity
    void updateEntityFromDto(BorrowerDTO dto, @MappingTarget Borrower entity);
    BorrowerDTO toBorrowerDTO(Borrower Borrower);
    Borrower toBorrower(BorrowerDTO borrowerDTO);
    List<BorrowerDTO> toBorrowerDTOList(List<Borrower> Borrowers);

    
    // --- Book ---
    BookDTO toBookDTO(Book Book);
    Book toBook(BookDTO bookDTO);
    List<BookDTO> toBookDTOList(List<Book> bookList);
    List<Book> toBookList(List<BookDTO> bookDTOList);

}
