package jcodingtime.java.exceptions;

/**
 * Exception Class for treatment of input empty
 */
public class InputEmptyException extends RuntimeException {
    /**
     * Constructor of exception class
     * @param message
     */
    public InputEmptyException(String message) {
        super(message);
    }
}

