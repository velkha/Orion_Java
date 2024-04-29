package org.orion.assistant.exception.custom.bbdd;

/**
 * This exception is thrown when a user already exists in the database.
 */
public class UserAlreadyExistException extends Exception{
    private static final long serialVersionUID = 1L;
    //private static Logger LOG = LogManager.getLogger(ProcessExecutionException.class);

    /**
     * Constructs a new UserAlreadyExistException with the specified detail message.
     *
     * @param message the detail message
     */
    public UserAlreadyExistException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserAlreadyExistException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new UserAlreadyExistException with the specified cause.
     *
     * @param cause the cause
     */
    public UserAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
