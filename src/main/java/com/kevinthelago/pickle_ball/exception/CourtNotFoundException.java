package com.kevinthelago.pickle_ball.exception;

public class CourtNotFoundException extends RuntimeException {
    public CourtNotFoundException(String message) {
        super(message);
    }

    public CourtNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
