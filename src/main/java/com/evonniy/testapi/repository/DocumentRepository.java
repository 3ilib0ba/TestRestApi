package com.evonniy.testapi.repository;

import com.evonniy.testapi.model.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> getDocumentById(Long id);


}
