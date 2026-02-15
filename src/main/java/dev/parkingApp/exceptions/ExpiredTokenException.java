package dev.parkingApp.exceptions;

public class ExpiredTokenException extends TokenException {
    public ExpiredTokenException(String message) {
        super(message);
    }
}
