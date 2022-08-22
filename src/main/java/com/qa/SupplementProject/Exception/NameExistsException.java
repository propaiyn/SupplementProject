package com.qa.SupplementProject.Exception;

public class NameExistsException extends AlreadyExistsException {
    public NameExistsException(String message) {
        super(message);
    }
}
