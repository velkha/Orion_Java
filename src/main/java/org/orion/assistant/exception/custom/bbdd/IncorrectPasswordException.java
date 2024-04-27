package org.orion.assistant.exception.custom.bbdd;

public class IncorrectPasswordException extends Exception {
    private static final long serialVersionUID = 1L;
    //private static Logger LOG = LogManager.getLogger(ProcessExecutionException.class);

    public IncorrectPasswordException(String message) {
        super(message);
    }

    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPasswordException(Throwable cause) {
        super(cause);
    }
}