package com.evonniy.testapi.exception.handlers;

import com.evonniy.testapi.exception.exceptions.MeetMustBeInFutureException;
import com.evonniy.testapi.exception.exceptions.YouAreNotAnOrganizatorException;
import com.evonniy.testapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class YouAreNotAnOrganizatorExceptionHandler {
    @ExceptionHandler(YouAreNotAnOrganizatorException.class)
    protected ResponseEntity<MessageDto> handleNotOrganizator() {
        return new ResponseEntity<>(new MessageDto("You must to be an organizator to do it"), HttpStatus.BAD_REQUEST);
    }
}
