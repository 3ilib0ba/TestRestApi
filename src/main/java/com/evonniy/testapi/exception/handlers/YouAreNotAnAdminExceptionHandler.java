package com.evonniy.testapi.exception.handlers;

import com.evonniy.testapi.exception.exceptions.YouAreNotAnAdminException;
import com.evonniy.testapi.exception.exceptions.YouAreNotAnOrganizatorException;
import com.evonniy.testapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class YouAreNotAnAdminExceptionHandler {
    @ExceptionHandler(YouAreNotAnAdminException.class)
    protected ResponseEntity<MessageDto> handleNotAdmin() {
        return new ResponseEntity<>(new MessageDto("You must to be an admin to do it"), HttpStatus.BAD_REQUEST);
    }
}
