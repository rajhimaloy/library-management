package com.erp.librarymanagement.model.mapper;

import com.erp.librarymanagement.model.entities.*;
import org.mapstruct.*;

import java.util.List;

/*
 * Author: Rajib Kumer Ghosh
 *
 * componentModel = "spring" ensures Spring injects the mapper as a bean (@Autowired or constructor).
 * List conversions are declared explicitly, allowing seamless mapping of collections.
 * You can add @Mapping annotations if field names differ between DTO and Entity (not needed now).
 * nullValuePropertyMappingStrategy prevents overwriting existing entity fields with null values from the DTO.
 */

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {

    // --- Student ---
    Student toEntity(StudentDTO studentDTO); // DTO → Entity ✅
    void updateEntityFromDto(StudentDTO dto, @MappingTarget Student entity);
    StudentDTO toStudentDTO(Student student);
    Student toStudent(CreateStudentRequest request);
    Student toStudent(UpdateStudentRequest request);
    List<StudentDTO> toStudentDTOs(List<Student> students);

    // --- StudentAddress ---
    StudentAddressDTO toStudentAddressDTO(StudentAddress address);
    StudentAddress toStudentAddress(StudentAddressDTO dto);
    List<StudentAddressDTO> toStudentAddressDTOs(List<StudentAddress> addresses);
    List<StudentAddress> toStudentAddresses(List<StudentAddressDTO> dtos);

    // --- Guardian ---
    GuardianDTO toGuardianDTO(Guardian guardian);
    Guardian toGuardian(GuardianDTO dto);
    List<GuardianDTO> toGuardianDTOs(List<Guardian> guardians);
    List<Guardian> toGuardians(List<GuardianDTO> dtos);

    // --- Enrollment ---
    EnrollmentDTO toEnrollmentDTO(Enrollment enrollment);
    Enrollment toEnrollment(EnrollmentDTO dto);
    List<EnrollmentDTO> toEnrollmentDTOs(List<Enrollment> enrollments);
    List<Enrollment> toEnrollments(List<EnrollmentDTO> dtos);

    // --- StudentDocument ---
    StudentDocumentDTO toStudentDocumentDTO(StudentDocument doc);
    StudentDocument toStudentDocument(StudentDocumentDTO dto);
    List<StudentDocumentDTO> toStudentDocumentDTOs(List<StudentDocument> docs);
    List<StudentDocument> toStudentDocuments(List<StudentDocumentDTO> dtos);

    // --- StudentAccount ---
    StudentAccountDTO toStudentAccountDTO(StudentAccount account);
    StudentAccount toStudentAccount(StudentAccountDTO dto);
}
