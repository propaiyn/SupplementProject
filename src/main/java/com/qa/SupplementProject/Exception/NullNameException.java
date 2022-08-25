package com.qa.SupplementProject.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Please enter the name of the supplement")
public class NullNameException extends RuntimeException {
    public NullNameException(String message) {
        super(message);
        System.out.println(message);
    }
}