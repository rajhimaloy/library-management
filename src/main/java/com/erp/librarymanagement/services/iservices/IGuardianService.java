package com.erp.librarymanagement.services.iservices;

import java.util.List;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

public interface IGuardianService {
    GuardianDTO addGuardian(UUID studentId, GuardianDTO guardianDTO);
    List<GuardianDTO> getGuardiansByStudentId(UUID studentId);
    GuardianDTO updateGuardian(UUID guardianId, GuardianDTO guardianDTO);
    void deleteGuardian(UUID guardianId);
}
