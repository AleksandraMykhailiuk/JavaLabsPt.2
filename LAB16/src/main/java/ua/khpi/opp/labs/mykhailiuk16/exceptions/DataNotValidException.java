package ua.khpi.opp.labs.mykhailiuk16.exceptions;

/**
 * Custom exception for case if inserted value is not valid
 */
public class DataNotValidException extends RuntimeException {
    public DataNotValidException(String message) {
        super(message);
    }
}
