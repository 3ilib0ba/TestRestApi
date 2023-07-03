package com.evonniy.testapi.service;

import com.evonniy.testapi.exception.exceptions.YouCantCreateAnotherDocumentException;
import com.evonniy.testapi.exception.exceptions.YouHaveNotDocumentException;
import com.evonniy.testapi.model.dto.DocumentDto;
import com.evonniy.testapi.model.entity.Document;
import com.evonniy.testapi.model.entity.User;
import com.evonniy.testapi.model.mapper.DocumentMapper;
import com.evonniy.testapi.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final UserService userService;

    public DocumentService(
            DocumentRepository documentRepository,
            DocumentMapper documentMapper,
            UserService userService
    ) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.userService = userService;
    }

    public List<DocumentDto> getAll() {
        return documentMapper.toListDtoFromList(documentRepository.findAll());
    }

    public DocumentDto createDocument(String documentText) {
        User organizator = userService.checkForOrganizatorAndGetIt();
        if (checkDocumentById(organizator.getId())) {
            throw new YouCantCreateAnotherDocumentException();
        }

        Document document = new Document();
        document.setOrganizator(organizator);
        document.setDocumentText(documentText);
        document.setSigned(false);

        return documentMapper.toDto(documentRepository.save(document));
    }

    private boolean checkDocumentById(long documentId) {
        return documentRepository.getDocumentById(documentId).isPresent();
    }

    public DocumentDto getMyDocument() {
        User organizator = userService.checkForOrganizatorAndGetIt();
        Optional<Document> isDocument = documentRepository.getDocumentById(organizator.getId());
        if (isDocument.isEmpty()) {
            throw new YouHaveNotDocumentException();
        }
        Document document = isDocument.get();

        return documentMapper.toDto(document);
    }

}
