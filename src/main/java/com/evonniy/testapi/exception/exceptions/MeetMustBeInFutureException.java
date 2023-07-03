package com.evonniy.testapi.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Meeting must be in the future")
public class MeetMustBeInFutureException extends RuntimeException {

}
