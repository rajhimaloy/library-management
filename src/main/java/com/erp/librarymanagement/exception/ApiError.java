package com.erp.librarymanagement.exception;

import java.time.OffsetDateTime;

/*
 * Author: Rajib Kumer Ghosh
 */

public record ApiError(String code, String message, OffsetDateTime timestamp) {
}
