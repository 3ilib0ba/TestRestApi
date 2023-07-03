package com.evonniy.testapi.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "You are already signed up for this meeting")
public class YouAreAlreadyAtSignedUpForMeetingException extends RuntimeException {
}
