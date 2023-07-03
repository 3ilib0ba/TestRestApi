package com.evonniy.testapi.service;

import com.evonniy.testapi.model.dto.DocumentDto;
import com.evonniy.testapi.model.mapper.DocumentMapper;
import com.evonniy.testapi.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public DocumentService(
            DocumentRepository documentRepository,
            DocumentMapper documentMapper
    ) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    public List<DocumentDto> getAll() {
        return documentMapper.toListDtoFromList(documentRepository.findAll());
    }

}
