package com.qa.SupplementProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A supplement with the provided PubChemID already exists")
public class PubChemIdExistsException extends AlreadyExistsException{ //Narrows down pre-existing exception
    public PubChemIdExistsException(String message) {
        super(message + "A supplement with the provided PubChemID already exists");
        System.out.println(message);
    }
}
