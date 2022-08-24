package com.qa.SupplementProject.Exception;

public class PubChemIdExistsException extends AlreadyExistsException{ //Narrows down pre-existing exception
    public PubChemIdExistsException(String message) {
        super(message);
    }
}
