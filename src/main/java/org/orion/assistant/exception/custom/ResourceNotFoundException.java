package org.orion.assistant.exception.custom;

import java.io.IOException;

/**
 * Custom exception for resource not found
 */
public class ResourceNotFoundException extends IOException {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     * 
     * @param message the detail message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ResourceNotFoundException with the specified cause.
     * 
     * @param cause the cause
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
