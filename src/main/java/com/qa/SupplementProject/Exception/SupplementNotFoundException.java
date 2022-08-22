package com.qa.SupplementProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Supplement does not exist with that ID")
public class SupplementNotFoundException extends RuntimeException {

    public SupplementNotFoundException(String message) {
        super(message);
    }
}