package org.orion.assistant.exception.handlers;

import org.orion.assistant.exception.custom.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalProcessExceptionHandler {
    /**
     * Handle the exception when a resource is not found
     * @param ex
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(ResourceNotFoundException ex) {
        // handle exception
        return ResponseEntity.notFound().build();
    }
}
