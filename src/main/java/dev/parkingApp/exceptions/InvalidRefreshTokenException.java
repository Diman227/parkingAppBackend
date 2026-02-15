package dev.parkingApp.exceptions;

public class InvalidRefreshTokenException extends TokenException {
    public InvalidRefreshTokenException(String message) {
        super(message);
    }
}
