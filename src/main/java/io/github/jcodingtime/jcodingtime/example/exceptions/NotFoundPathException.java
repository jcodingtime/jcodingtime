package io.github.jcodingtime.jcodingtime.example.exceptions;

/**
 * Exception Class for treatment of not found path
 */
public class NotFoundPathException extends RuntimeException {
    /**
     * Constructor of exception class
     * @param message
     */
    public NotFoundPathException(String message) {
        super(message);
    }
}
