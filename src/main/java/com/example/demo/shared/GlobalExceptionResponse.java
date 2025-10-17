package com.example.demo.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionResponse {
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GlobalResponse<?>> handleNoResourceFoundException(NoResourceFoundException ex) {
        var errors = List.of(new GlobalResponse.ErrorItem("Ressource is not found!"));
        return new ResponseEntity<>(new GlobalResponse<>(errors), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CustomResponseException.class)
    public ResponseEntity<GlobalResponse<?>> handleCustomResponseException(CustomResponseException ex) {
        var errors = List.of(new GlobalResponse.ErrorItem(ex.getMessage()));
        return new ResponseEntity<>(new GlobalResponse<>(errors), HttpStatus.BAD_REQUEST);
    }
}
