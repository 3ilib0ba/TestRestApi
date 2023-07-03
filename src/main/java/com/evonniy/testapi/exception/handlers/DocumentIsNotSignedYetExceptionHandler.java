package com.evonniy.testapi.exception.handlers;

import com.evonniy.testapi.exception.exceptions.DocumentIsNotSignedYetException;
import com.evonniy.testapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DocumentIsNotSignedYetExceptionHandler {
    @ExceptionHandler(DocumentIsNotSignedYetException.class)
    protected ResponseEntity<MessageDto> handleDocumentNotSignedYet() {
        return new ResponseEntity<>(new MessageDto("your document isn't signed yet"), HttpStatus.OK);
    }
}
