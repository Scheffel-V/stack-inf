package utils;

/**
 * @author lmrodrigues
 * 
 */

public class UserException extends Exception {

    private String message;

    public UserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
