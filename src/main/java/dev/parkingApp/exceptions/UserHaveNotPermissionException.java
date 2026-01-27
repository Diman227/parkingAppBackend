package dev.parkingApp.exceptions;

public class UserHaveNotPermissionException extends RuntimeException {

    public UserHaveNotPermissionException(String message) {
        super(message);
    }
}
