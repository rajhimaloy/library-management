package com.erp.librarymanagement.services.iservices;

import java.util.List;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

public interface IStudentService {
    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO getStudentById(UUID studentId);
    List<StudentDTO> getAllStudents();
    StudentDTO updateStudent(UUID studentId, StudentDTO studentDTO);
    void deleteStudent(UUID studentId);
}
