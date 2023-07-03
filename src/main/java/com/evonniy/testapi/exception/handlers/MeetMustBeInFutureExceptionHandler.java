package com.evonniy.testapi.exception.handlers;

import com.evonniy.testapi.exception.exceptions.MeetMustBeInFutureException;
import com.evonniy.testapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeetMustBeInFutureExceptionHandler {

    @ExceptionHandler(MeetMustBeInFutureException.class)
    protected ResponseEntity<MessageDto> meetBadDate() {
        return new ResponseEntity<>(new MessageDto("Meeting must be in the future"), HttpStatus.BAD_REQUEST);
    }
}
