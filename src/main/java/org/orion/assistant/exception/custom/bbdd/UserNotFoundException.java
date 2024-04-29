package org.orion.assistant.exception.custom.bbdd;

/**
 * This exception is thrown when a user is not found in the database.
 */
public class UserNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;
    //private static Logger LOG = LogManager.getLogger(ProcessExecutionException.class);

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new UserNotFoundException with the specified cause.
     *
     * @param cause the cause
     */
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}