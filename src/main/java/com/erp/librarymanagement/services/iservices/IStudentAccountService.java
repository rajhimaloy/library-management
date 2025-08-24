package com.erp.librarymanagement.services.iservices;

import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

public interface IStudentAccountService {
    StudentAccountDTO createAccount(UUID studentId, StudentAccountDTO accountDTO);
    StudentAccountDTO getAccountByStudentId(UUID studentId);
    StudentAccountDTO updateAccount(UUID accountId, StudentAccountDTO accountDTO);
    void deleteAccount(UUID accountId);
}
