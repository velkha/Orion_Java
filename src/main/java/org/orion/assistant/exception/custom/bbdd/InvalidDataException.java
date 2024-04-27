package org.orion.assistant.exception.custom.bbdd;

import java.sql.SQLIntegrityConstraintViolationException;

public class InvalidDataException extends SQLIntegrityConstraintViolationException{
    
        public InvalidDataException(String message) {
            super(message);
        }

        public InvalidDataException(String message, Throwable cause) {
            super(message, cause);
        }

        public InvalidDataException(Throwable cause) {
            super(cause);
        }
    
}
