package com.qa.SupplementProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No such supplement has been found")
public class IDNotFoundException extends RuntimeException {

    public IDNotFoundException(String message) {
        super(message);
    }
}
