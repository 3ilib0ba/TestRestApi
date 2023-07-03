package com.evonniy.testapi.exception.handlers;

import com.evonniy.testapi.exception.exceptions.YouCantCreateAnotherDocumentException;
import com.evonniy.testapi.exception.exceptions.YouHaveNotDocumentException;
import com.evonniy.testapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class YouHaveNotDocumentExceptionHandler {
    @ExceptionHandler(YouHaveNotDocumentException.class)
    protected ResponseEntity<MessageDto> handleOrganizatorHaveNotDocument() {
        return new ResponseEntity<>(new MessageDto("you haven't any document"), HttpStatus.OK);
    }
}
