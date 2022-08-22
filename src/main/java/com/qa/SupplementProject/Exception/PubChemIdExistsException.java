package com.qa.SupplementProject.Exception;

public class PubChemIdExistsException extends AlreadyExistsException{
    public PubChemIdExistsException(String message) {
        super(message);
    }
}
