package com.qa.SupplementProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No supplement with the specified name was found")
public class NameNotFoundException extends RuntimeException {
    public NameNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }
}
