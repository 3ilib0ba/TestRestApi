package com.evonniy.testapi.exception.handlers;

import com.evonniy.testapi.exception.exceptions.YouAreNotAuthorizedException;
import com.evonniy.testapi.exception.exceptions.YouCantCreateAnotherDocumentException;
import com.evonniy.testapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class YouCantCreateAnotherDocumentExceptionHandler {
    @ExceptionHandler(YouCantCreateAnotherDocumentException.class)
    protected ResponseEntity<MessageDto> handleAttemptToCreateAnotherDocument() {
        return new ResponseEntity<>(new MessageDto("you already have a document with our company"), HttpStatus.OK);
    }
}
