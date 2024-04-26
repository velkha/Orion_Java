package org.orion.assistant.exception.custom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProcessExecutionException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private static Logger LOG = LogManager.getLogger(ProcessExecutionException.class);

    public ProcessExecutionException(String message) {
        super(message);
    }

    public ProcessExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessExecutionException(Throwable cause) {
        super(cause);
    }
    
}
