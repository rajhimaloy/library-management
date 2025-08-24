package com.erp.librarymanagement.services.iservices;

import java.util.List;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

public interface IEnrollmentService {
    EnrollmentDTO enrollStudent(UUID studentId, EnrollmentDTO enrollmentDTO);
    List<EnrollmentDTO> getEnrollmentsByStudentId(UUID studentId);
    EnrollmentDTO updateEnrollment(UUID enrollmentId, EnrollmentDTO enrollmentDTO);
    void deleteEnrollment(UUID enrollmentId);
}
