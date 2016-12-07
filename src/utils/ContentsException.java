package utils;

/**
 * @author lmrodrigues
 * 
 */

public class ContentsException extends Exception {

    private static final long serialVersionUID = 8666360602455146407L;

    public ContentsException() {
        super();
    }

    public ContentsException(String message) {
        super(message);
    }

    public ContentsException(String message, Throwable cause) {
        super(message, cause);
    }

}
