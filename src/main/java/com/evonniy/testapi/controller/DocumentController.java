package com.evonniy.testapi.controller;

import com.evonniy.testapi.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping(value = "/documents/get_all")
    public ResponseEntity<?> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAll());
    }

    @GetMapping(value = "/documents/get_my")
    public ResponseEntity<?> getSelfDocument() {
        return ResponseEntity.ok(documentService.getMyDocument());
    }

    @PostMapping(value = "/documents/create")
    public ResponseEntity<?> createDocumentAsOrganizator(
            @RequestBody @NotBlank String documentText
    ) {
        return ResponseEntity.ok(documentService.createDocument(documentText));
    }

    @PostMapping(value = "/documents/admin/sign_document")
    public ResponseEntity<?> signCurrentDocument(
            @RequestParam @Min(1) long documentId
    ) {
        return ResponseEntity.ok(documentService.signDocument(documentId));
    }
}
