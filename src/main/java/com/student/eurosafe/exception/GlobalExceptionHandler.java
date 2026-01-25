package com.student.eurosafe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError; // New import
import org.springframework.web.bind.MethodArgumentNotValidException; // New import
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // --- NEW: Handles Validation Errors (Day 12/13) ---
    // If a user forgets Latitude or Description, this runs.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = new HashMap<>();

        // Loop through all the failures (e.g., "Latitude required")
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Return the clean list with a 400 Bad Request
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // --- OLD: Handles Generic Runtime Errors (From before) ---
    // If "Username is taken", this runs.
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("status", "400");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}