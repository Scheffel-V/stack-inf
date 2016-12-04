package utils;

/**
 * @author lmrodrigues
 * 
 */

public class ContentsException extends Exception {

    private String message;

    public ContentsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
