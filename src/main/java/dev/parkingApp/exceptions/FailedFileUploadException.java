package dev.parkingApp.exceptions;

public class FailedFileUploadException extends  RuntimeException {

    public FailedFileUploadException(String message) {
        super(message);
    }
}
