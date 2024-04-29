package org.orion.assistant.exception.custom.bbdd;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * This exception is thrown when invalid data is encountered during database operations.
 * It extends the SQLIntegrityConstraintViolationException class.
 */
public class InvalidDataException extends SQLIntegrityConstraintViolationException {
    
    /**
     * Constructs a new InvalidDataException with the specified detail message.
     * 
     * @param message the detail message
     */
    public InvalidDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidDataException with the specified detail message and cause.
     * 
     * @param message the detail message
     * @param cause the cause
     */
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new InvalidDataException with the specified cause.
     * 
     * @param cause the cause
     */
    public InvalidDataException(Throwable cause) {
        super(cause);
    }
    
}
