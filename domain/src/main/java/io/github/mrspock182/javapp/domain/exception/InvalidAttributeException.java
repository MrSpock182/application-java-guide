package io.github.mrspock182.javapp.domain.exception;

public class InvalidAttributeException extends RuntimeException {
    public InvalidAttributeException() {
        super();
    }

    public InvalidAttributeException(String message) {
        super(message);
    }

    public InvalidAttributeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAttributeException(Throwable cause) {
        super(cause);
    }
}
