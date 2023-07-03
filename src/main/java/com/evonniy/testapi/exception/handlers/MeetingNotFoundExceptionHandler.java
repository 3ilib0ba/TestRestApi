package com.evonniy.testapi.exception.handlers;

import com.evonniy.testapi.exception.exceptions.MeetingNotFoundException;
import com.evonniy.testapi.exception.exceptions.YouAreNotAnOrganizatorException;
import com.evonniy.testapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeetingNotFoundExceptionHandler {
    @ExceptionHandler(MeetingNotFoundException.class)
    protected ResponseEntity<MessageDto> handleNotFoundMeeting() {
        return new ResponseEntity<>(new MessageDto("Meeting not found"), HttpStatus.BAD_REQUEST);
    }
}
