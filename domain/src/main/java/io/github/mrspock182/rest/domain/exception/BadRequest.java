package io.github.mrspock182.rest.domain.exception;

public class BadRequest extends RuntimeException {
    public BadRequest() {
        super();
    }

    public BadRequest(String message) {
        super(message);
    }

    public BadRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequest(Throwable cause) {
        super(cause);
    }
}
