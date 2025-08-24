package com.erp.librarymanagement.services.impl;

import com.erp.librarymanagement.services.iservices.IHelloServices;
import org.springframework.stereotype.Service;

import java.util.Map;

/*
 * Author: Rajib Kumer Ghosh
 */

@Service
public class HelloServices implements IHelloServices {

    @Override
    public String getByName(String name) {
        StudentV1 student = new StudentV1();
        student.setId(1001);
        student.setName(name);
        student.setAge(38);
        return "Student Details: ID = " + student.getId() + ", Name = " + student.getName() + ", Age = " + student.getAge();
    }

    @Override
    public String createStudent(StudentV1 student) {
        return "Student Created: ID = " + student.getId() + ", Name = " + student.getName() + ", Age = " + student.getAge();
    }

    @Override
    public String updateStudent(StudentV1 student) {
        return "Student Updated: ID = " + student.getId() + ", Name = " + student.getName() + ", Age = " + student.getAge();
    }

    @Override
    public String updateStudentAge(Integer id, Integer age) {
        return "Student Partial Update with ID " + id + ", updated field: " + age;
    }

    @Override
    public String updateStudentPartial(Integer id, Map<String, Object> student) {
        return "Student with ID " + id + " updated fields: " + student;
    }

    @Override
    public String deleteStudent(StudentV1 student) {
        return "Student Deleted: ID = " + student.getId() + ", Name = " + student.getName() + ", Age = " + student.getAge();
    }
}
