package com.qa.SupplementProject.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{

    @ExceptionHandler(IDNotFoundException.class)
    public ResponseEntity<Object> handleIDNotFoundException(
            IDNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", "No supplement with that ID was found");
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<Object> handleNameNotFoundException(
            NameNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", "No supplement with that name was found");
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PubChemIdExistsException.class)
    public ResponseEntity<Object> handlePubChemIdExistsException(
            PubChemIdExistsException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", "A supplement with the same PubChem ID already exists");
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.ALREADY_REPORTED);
    }
    @ExceptionHandler(NameExistsException.class)
    public ResponseEntity<Object> handleNameExistsException(
            NameExistsException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", "A supplement with the same name already exists");
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(NullNameException.class)
    public ResponseEntity<Object> handleNullNameException(
            NullNameException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", "Please enter the name of the supplement");
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPCIDException.class)
    public ResponseEntity<Object> handleNullPCIDException(
            NullPCIDException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", "Please enter the PubChemID of the supplement");
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}