package com.evonniy.testapi.exception.handlers;

import com.evonniy.testapi.exception.exceptions.UserNotFoundException;
import com.evonniy.testapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<MessageDto> handleUserNotFoundException() {
        return new ResponseEntity<>(new MessageDto("user not found"), HttpStatus.OK);
    }

}
