package com.erp.librarymanagement.services.iservices;

import java.util.List;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

public interface IStudentAddressService {
    StudentAddressDTO addAddress(UUID studentId, StudentAddressDTO addressDTO);
    List<StudentAddressDTO> getAddressesByStudentId(UUID studentId);
    StudentAddressDTO updateAddress(UUID addressId, StudentAddressDTO addressDTO);
    void deleteAddress(UUID addressId);
}
