package com.evonniy.testapi.controller;

import com.evonniy.testapi.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
