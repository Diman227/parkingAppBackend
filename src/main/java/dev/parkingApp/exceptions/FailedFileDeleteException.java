package dev.parkingApp.exceptions;

public class FailedFileDeleteException extends RuntimeException {
    public FailedFileDeleteException(String message) {
        super(message);
    }
}
