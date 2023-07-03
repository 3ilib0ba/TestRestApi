package com.evonniy.testapi.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "You are not an organizator")
public class YouAreNotAnOrganizatorException extends RuntimeException{
}
