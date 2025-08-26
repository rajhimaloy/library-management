package com.erp.librarymanagement.model.mapper;

import com.erp.librarymanagement.model.dto.BookDTO;
import com.erp.librarymanagement.model.dto.BorrowerDTO;
import com.erp.librarymanagement.model.entities.Book;
import com.erp.librarymanagement.model.entities.Borrower;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-27T07:39:20+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class DtoEntityMapperImpl implements DtoEntityMapper {

    @Override
    public Borrower toEntity(BorrowerDTO borrowerDTO) {
        if ( borrowerDTO == null ) {
            return null;
        }

        Borrower borrower = new Borrower();

        borrower.setId( borrowerDTO.getId() );
        borrower.setName( borrowerDTO.getName() );
        borrower.setEmail( borrowerDTO.getEmail() );
        borrower.setStatus( borrowerDTO.getStatus() );

        return borrower;
    }

    @Override
    public void updateEntityFromDto(BorrowerDTO dto, Borrower entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
    }

    @Override
    public BorrowerDTO toBorrowerDTO(Borrower Borrower) {
        if ( Borrower == null ) {
            return null;
        }

        BorrowerDTO borrowerDTO = new BorrowerDTO();

        borrowerDTO.setId( Borrower.getId() );
        borrowerDTO.setName( Borrower.getName() );
        borrowerDTO.setEmail( Borrower.getEmail() );
        borrowerDTO.setStatus( Borrower.getStatus() );

        return borrowerDTO;
    }

    @Override
    public Borrower toBorrower(BorrowerDTO borrowerDTO) {
        if ( borrowerDTO == null ) {
            return null;
        }

        Borrower borrower = new Borrower();

        borrower.setId( borrowerDTO.getId() );
        borrower.setName( borrowerDTO.getName() );
        borrower.setEmail( borrowerDTO.getEmail() );
        borrower.setStatus( borrowerDTO.getStatus() );

        return borrower;
    }

    @Override
    public List<BorrowerDTO> toBorrowerDTOList(List<Borrower> Borrowers) {
        if ( Borrowers == null ) {
            return null;
        }

        List<BorrowerDTO> list = new ArrayList<BorrowerDTO>( Borrowers.size() );
        for ( Borrower borrower : Borrowers ) {
            list.add( toBorrowerDTO( borrower ) );
        }

        return list;
    }

    @Override
    public BookDTO toBookDTO(Book Book) {
        if ( Book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( Book.getId() );

        return bookDTO;
    }

    @Override
    public Book toBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDTO.getId() );

        return book;
    }

    @Override
    public List<BookDTO> toBookDTOList(List<Book> bookList) {
        if ( bookList == null ) {
            return null;
        }

        List<BookDTO> list = new ArrayList<BookDTO>( bookList.size() );
        for ( Book book : bookList ) {
            list.add( toBookDTO( book ) );
        }

        return list;
    }

    @Override
    public List<Book> toBookList(List<BookDTO> bookDTOList) {
        if ( bookDTOList == null ) {
            return null;
        }

        List<Book> list = new ArrayList<Book>( bookDTOList.size() );
        for ( BookDTO bookDTO : bookDTOList ) {
            list.add( toBook( bookDTO ) );
        }

        return list;
    }
}
