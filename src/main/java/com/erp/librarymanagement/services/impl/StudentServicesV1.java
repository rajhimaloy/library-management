package com.erp.librarymanagement.services.impl;

import com.erp.librarymanagement.services.iservices.IStudentServicesV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * Author: Rajib Kumer Ghosh
 */

@CacheConfig(cacheNames = "student")
@Service
public class StudentServicesV1 implements IStudentServicesV1 {

    @Autowired
    private IStudentRepositoryV1 iStudentRepositoryV1;

    public StudentServicesV1(IStudentRepositoryV1 iStudentRepositoryV1) {
        this.iStudentRepositoryV1 = iStudentRepositoryV1;
    }

    /*Data JPA CRUD*/

    // Retrieve all students and cache the result
    @Cacheable(value = "students")
    @Override
    public List<StudentV1> getAllStudents() throws Exception {
        return iStudentRepositoryV1.findAll();
    }

    // Retrieve an student by ID and cache the result
    @Cacheable(value = "student", key = "#id")
    @Override
    public Optional<StudentV1> getStudentById(Integer id) throws Exception {
        return Optional.ofNullable(iStudentRepositoryV1.findById(id).orElseThrow(() -> new Exception("Student Not Found")));
    }

    // Create a new student and evict the cache for student
    //@CacheEvict(cacheNames = "students", allEntries = true)
    @CachePut(value = "students", key = "#student.id")
    @Override
    public StudentV1 saveStudent(StudentV1 student) {
        return iStudentRepositoryV1.save(student);
    }

    // Update an existing student and manage cache eviction
    @CachePut(value = "students", key = "#student.id")
    @Override
    public StudentV1 update(StudentV1 student) throws Exception {
        Optional<StudentV1> existingStudent = Optional.ofNullable(iStudentRepositoryV1.findById(student.getId()).orElseThrow(() -> new Exception("Student Not Found")));
        StudentV1 studentUpdate = existingStudent.get();
        studentUpdate.setName(student.getName());
        studentUpdate.setAge(student.getAge());
        //student.setDescription(student.getDescription());
        return iStudentRepositoryV1.save(studentUpdate);
    }

    // Update an existing student and manage cache eviction
    @CachePut(value = "students", key = "#id")
    @Override
    public StudentV1 updatePartial(Integer id, StudentV1 student) throws Exception {
        Optional<StudentV1> existingStudent = Optional.ofNullable(iStudentRepositoryV1.findById(id).orElseThrow(() -> new Exception("Student Not Found")));
        StudentV1 studentUpdate = existingStudent.get();
        //studentUpdate.setName(student.getName());
        studentUpdate.setAge(student.getAge());
        return iStudentRepositoryV1.save(studentUpdate);
    }

    // Delete an student and manage cache eviction
    //@CacheEvict(value = "students", key = "#id") // Evicts the specific student and clears the specific student from the cache
    @CacheEvict(cacheNames = {"student", "students"}, allEntries = true, key = "#id") // Evicts the specific student and clears all students from the cache
    @Override
    public void deleteStudent(Integer id) throws Exception {
        if(!iStudentRepositoryV1.existsById(id)) {
            throw new Exception("Student Not Found");
        }

        iStudentRepositoryV1.deleteById(id);
    }
}
