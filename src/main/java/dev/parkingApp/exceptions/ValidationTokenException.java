package dev.parkingApp.exceptions;

public class ValidationTokenException extends RuntimeException {
    public ValidationTokenException(String message) {
        super(message);
    }
}
