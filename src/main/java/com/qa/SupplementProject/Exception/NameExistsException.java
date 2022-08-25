package com.qa.SupplementProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A supplement with this name already exists")
public class NameExistsException extends AlreadyExistsException { //Narrows down pre-existing exception
    public NameExistsException(String message) {
        super(message + "A supplement with this name already exists");
        System.out.println(message);
    }
}
