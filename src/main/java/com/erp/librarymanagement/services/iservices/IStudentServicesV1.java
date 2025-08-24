package com.erp.librarymanagement.services.iservices;

import java.util.List;
import java.util.Optional;

/*
 * Author: Rajib Kumer Ghosh
 */

public interface IStudentServicesV1 {

    /*Data JPA CRUD*/
    public List<StudentV1> getAllStudents() throws Exception;
    public Optional<StudentV1> getStudentById(Integer id) throws Exception;
    public StudentV1 saveStudent(StudentV1 student);
    public StudentV1 update(StudentV1 student) throws Exception;
    public StudentV1 updatePartial(Integer id, StudentV1 student) throws Exception;
    public void deleteStudent(Integer id) throws Exception;
}
