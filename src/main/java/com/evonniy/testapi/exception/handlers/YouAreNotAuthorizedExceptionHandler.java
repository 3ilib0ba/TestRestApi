package com.evonniy.testapi.exception.handlers;

import com.evonniy.testapi.exception.exceptions.YouAreNotAuthorizedException;
import com.evonniy.testapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class YouAreNotAuthorizedExceptionHandler {
    @ExceptionHandler(YouAreNotAuthorizedException.class)
    protected ResponseEntity<MessageDto> handleNotAuthorizedUser() {
        return new ResponseEntity<>(new MessageDto("you have to be authorized for it"), HttpStatus.UNAUTHORIZED);
    }
}
