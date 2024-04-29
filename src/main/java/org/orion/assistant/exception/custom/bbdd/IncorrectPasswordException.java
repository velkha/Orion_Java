package org.orion.assistant.exception.custom.bbdd;

/**
 * This exception is thrown when an incorrect password is provided.
 */
public class IncorrectPasswordException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new IncorrectPasswordException with the specified detail message.
     *
     * @param message the detail message
     */
    public IncorrectPasswordException(String message) {
        super(message);
    }

    /**
     * Constructs a new IncorrectPasswordException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new IncorrectPasswordException with the specified cause.
     *
     * @param cause the cause
     */
    public IncorrectPasswordException(Throwable cause) {
        super(cause);
    }
}