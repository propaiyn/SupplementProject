package com.qa.SupplementProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Please enter the PubChemID of the supplement")
public class NullPCIDException extends RuntimeException {
    public NullPCIDException(String message) {
        super(message);
        System.out.println(message);
    }
}