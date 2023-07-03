package com.evonniy.testapi.model.mapper;

import com.evonniy.testapi.model.dto.DocumentDto;
import com.evonniy.testapi.model.entity.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentMapper {
    private final UserMapper userMapper;

    public DocumentMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public DocumentDto toDto(Document document) {
        return new DocumentDto(
                userMapper.toDto(document.getOrganizator()),
                document.getDocumentText(),
                document.isSigned()
        );
    }

    public List<DocumentDto> toListDtoFromList(List<Document> documents) {
        return documents.stream()
                .map(this::toDto)
                .toList();
    }
}
