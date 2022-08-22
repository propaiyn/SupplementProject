package com.qa.SupplementProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Please provide enter response")
public class IsNullException extends RuntimeException {

    public IsNullException(String message) {
        super(message);
    }
}
