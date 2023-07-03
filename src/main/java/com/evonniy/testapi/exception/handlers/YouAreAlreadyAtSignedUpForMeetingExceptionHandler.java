package com.evonniy.testapi.exception.handlers;

import com.evonniy.testapi.exception.exceptions.YouAreAlreadyAtSignedUpForMeetingException;
import com.evonniy.testapi.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class YouAreAlreadyAtSignedUpForMeetingExceptionHandler {
    @ExceptionHandler(YouAreAlreadyAtSignedUpForMeetingException.class)
    protected ResponseEntity<MessageDto> handleUserAlreadyIsParticipant() {
        return new ResponseEntity<>(new MessageDto("You are already signed up for this meeting"), HttpStatus.BAD_REQUEST);
    }
}
