package com.evonniy.testapi.controller;

import com.evonniy.testapi.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
