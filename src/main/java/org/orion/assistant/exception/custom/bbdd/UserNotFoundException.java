package org.orion.assistant.exception.custom.bbdd;

public class UserNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;
    //private static Logger LOG = LogManager.getLogger(ProcessExecutionException.class);

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}