package org.orion.assistant.exception.handlers;

import org.orion.assistant.exception.custom.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for processing exceptions in the application.
 */
@RestControllerAdvice
public class GlobalProcessExceptionHandler {
    /**
     * Handle the exception when a resource is not found.
     * 
     * @param ex the ResourceNotFoundException to be handled
     * @return a ResponseEntity with a not found status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(ResourceNotFoundException ex) {
        // handle exception
        return ResponseEntity.notFound().build();
    }
}
