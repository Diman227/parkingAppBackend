package dev.parkingApp.exceptions;

public class SpotNotFoundException extends RuntimeException {

    public SpotNotFoundException(String message) {
        super(message);
    }
}
