package utils;

/**
 * @author lmrodrigues
 * 
 */

public class UserException extends Exception {

    private static final long serialVersionUID = 2059533965342636377L;

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
