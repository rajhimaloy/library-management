package com.erp.librarymanagement.exception;

/*
 * Author: Rajib Kumer Ghosh
 */

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
