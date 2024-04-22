package org.orion.assistant.exception.custom;

import java.io.IOException;

public class ResourceNotFoundException extends IOException {
        private static final long serialVersionUID = 1L;
    
        public ResourceNotFoundException(String message) {
            super(message);
        }
    
        public ResourceNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    
        public ResourceNotFoundException(Throwable cause) {
            super(cause);
        }
    
}
