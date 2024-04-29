package org.orion.assistant.exception.custom;

/**
 * This exception is thrown when there is an error during the execution of a process.
 */
public class ProcessExecutionException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ProcessExecutionException with the specified detail message.
     *
     * @param message the detail message
     */
    public ProcessExecutionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ProcessExecutionException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public ProcessExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ProcessExecutionException with the specified cause.
     *
     * @param cause the cause
     */
    public ProcessExecutionException(Throwable cause) {
        super(cause);
    }
    
}
