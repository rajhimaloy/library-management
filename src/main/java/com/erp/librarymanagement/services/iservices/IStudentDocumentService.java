package com.erp.librarymanagement.services.iservices;

import java.util.List;
import java.util.UUID;

/*
 * Author: Rajib Kumer Ghosh
 */

public interface IStudentDocumentService {
    StudentDocumentDTO uploadDocument(UUID studentId, StudentDocumentDTO documentDTO);
    List<StudentDocumentDTO> getDocumentsByStudentId(UUID studentId);
    StudentDocumentDTO updateDocument(UUID documentId, StudentDocumentDTO documentDTO);
    void deleteDocument(UUID documentId);
}
