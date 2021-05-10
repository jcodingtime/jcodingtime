package io.github.jcodingtime.jcodingtime.example.exceptions;

/**
 * Exception Class for treatment of match not exist
 */
public class MatchNotExistException extends RuntimeException {
    /**
     * Constructor of exception class
     * @param message
     */
    public MatchNotExistException(String message) {
        super(message);
    }
}

