package com.evonniy.testapi.exception.handlers;

import com.evonniy.testapi.exception.exceptions.UserAlreadyExistException;
import com.evonniy.testapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAlreadyExistExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    protected ResponseEntity<MessageDto> userAlreadyExist() {
        return new ResponseEntity<>(new MessageDto("user already exist"), HttpStatus.BAD_REQUEST);
    }
}
