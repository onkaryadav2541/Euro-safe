package com.student.eurosafe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // This method catches the RuntimeException we threw yesterday
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        
        // We take the message "Username is already taken" and put it in JSON
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("status", "400");

        // Return a 400 BAD REQUEST instead of 500 INTERNAL SERVER ERROR
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}