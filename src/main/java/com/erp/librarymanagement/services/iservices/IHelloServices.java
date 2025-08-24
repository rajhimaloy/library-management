package com.erp.librarymanagement.services.iservices;

import java.util.Map;

/*
 * Author: Rajib Kumer Ghosh
 */

public interface IHelloServices {
    public String getByName(String name);
    public String createStudent(StudentV1 student);
    public String updateStudent(StudentV1 student);
    public String updateStudentAge(Integer id, Integer age);
    public String updateStudentPartial(Integer id, Map<String, Object> student);
    public String deleteStudent(StudentV1 student);
}
