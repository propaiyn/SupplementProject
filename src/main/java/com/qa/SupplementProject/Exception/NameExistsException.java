package com.qa.SupplementProject.Exception;

public class NameExistsException extends AlreadyExistsException { //Narrows down pre-existing exception
    public NameExistsException(String message) {
        super(message);
    }
}
