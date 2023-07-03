package com.evonniy.testapi.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "your document isn't signed")
public class DocumentIsNotSignedYetException extends RuntimeException {
}
