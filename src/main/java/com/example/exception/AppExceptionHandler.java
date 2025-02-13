package com.example.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

/**
 * Class for handling exceptions for App.
 */
@ControllerAdvice
public class AppExceptionHandler {
    
    /**
     * Handles all uncaught exceptions that do not have a more specific handler.
     * @param e - {@link Exception}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e) {
        return ResponseEntity.badRequest().body("Bad request.");
    }

    /**
     * Handles ResponseStatusException.
     * @param e - {@link ResponseStatusException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}
