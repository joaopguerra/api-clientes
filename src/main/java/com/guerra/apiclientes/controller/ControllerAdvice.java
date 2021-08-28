package com.guerra.apiclientes.controller;

import com.guerra.apiclientes.exception.ClienteNotFound;
import com.guerra.apiclientes.exception.ConflictException;
import com.guerra.apiclientes.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ClienteNotFound.class)
    public ResponseEntity<StandardError> entityNotFound(ClienteNotFound e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(status.value());
        standardError.setError(status.name());
        standardError.setMessage(e.getMessage());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<StandardError> entityNotFound(ConflictException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(status.value());
        standardError.setError(status.name());
        standardError.setMessage(e.getMessage());
        return ResponseEntity.status(status).body(standardError);
    }
}
