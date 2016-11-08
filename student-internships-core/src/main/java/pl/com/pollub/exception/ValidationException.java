package pl.com.pollub.exception;

/**
 * Created by Maciek on 2016-11-04.
 */
public class ValidationException extends RuntimeException {

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
