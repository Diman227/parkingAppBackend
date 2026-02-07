package dev.parkingApp.exceptions;

public class SpotBusyException extends RuntimeException {

    public SpotBusyException(String message) {
        super(message);
    }
}
