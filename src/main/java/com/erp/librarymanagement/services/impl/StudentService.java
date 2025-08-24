package com.erp.librarymanagement.services.impl;

import com.erp.librarymanagement.exception.ResourceNotFoundException;
import com.erp.librarymanagement.services.iservices.IStudentService;
import com.erp.librarymanagement.model.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/*
 * Author: Rajib Kumer Ghosh
 */

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService implements IStudentService {

    private final IStudentRepository studentRepository;
    private final StudentMapper studentMapper;

    /*public StudentService(IStudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }*/

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {

        /*if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }*/

        // Map DTO to Entity
        Student student = studentMapper.toEntity(studentDTO);

        //log.info("Creating student: {}", studentDTO.getFirstName());

        // Save Entity
        Student savedStudent = studentRepository.save(student);

        // Map back to DTO
        return studentMapper.toStudentDTO(savedStudent);
    }

    @Override
    public StudentDTO getStudentById(UUID studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));
        return studentMapper.toStudentDTO(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(UUID studentId, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));

        // ✅ Use MapStruct to map fields. No need to manually set fields one by one.
        studentMapper.updateEntityFromDto(studentDTO, existingStudent);

        // Save changes
        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.toStudentDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(UUID studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with ID: " + studentId);
        }
        studentRepository.deleteById(studentId);
    }
}
