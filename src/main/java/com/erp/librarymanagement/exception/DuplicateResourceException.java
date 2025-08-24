package com.erp.librarymanagement.exception;

/*
 * Author: Rajib Kumer Ghosh
 * Purpose: Thrown when a resource (e.g., name, email ) already exists.
 */

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
