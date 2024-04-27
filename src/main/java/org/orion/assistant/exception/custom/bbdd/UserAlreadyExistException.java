package org.orion.assistant.exception.custom.bbdd;

public class UserAlreadyExistException extends Exception{
    private static final long serialVersionUID = 1L;
    //private static Logger LOG = LogManager.getLogger(ProcessExecutionException.class);

    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
