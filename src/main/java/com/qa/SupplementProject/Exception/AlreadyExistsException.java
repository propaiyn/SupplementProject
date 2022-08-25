package com.qa.SupplementProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "This supplement already exists")
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message + "This supplement already exists");
        System.out.println(message);
    }
}