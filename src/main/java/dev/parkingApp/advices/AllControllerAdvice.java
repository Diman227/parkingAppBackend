package dev.parkingApp.advices;

import dev.parkingApp.dtos.response.ExceptionResponse;
import dev.parkingApp.exceptions.*;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class AllControllerAdvice {

    @ExceptionHandler(SpotNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleSpotNotFoundException(SpotNotFoundException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()) ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleReviewNotFoundException(ReviewNotFoundException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserHaveNotPermissionException.class)
    public ResponseEntity<ExceptionResponse> handleUserHaveNotPermissionException(UserHaveNotPermissionException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
