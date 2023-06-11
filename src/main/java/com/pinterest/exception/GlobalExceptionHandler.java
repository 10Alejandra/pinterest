package com.pinterest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage<Error>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildError(ex, "0000", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotAuthorizedException.class)
    public ResponseEntity<ErrorMessage<Error>> handleResourceNotAuthorizedException(ResourceNotAuthorizedException ex) {
        return buildError(ex, "0001", HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<ErrorMessage<Error>> buildError(Exception ex, String code, HttpStatus httpStatus) {
        Error error = Error.builder().code(code).detail(ex.getMessage()).build();
        ErrorMessage<Error> errorMessage = ErrorMessage.<Error>builder()
                .status(httpStatus.value())
                .timestamp(new Date())
                .title(httpStatus.getReasonPhrase())
                .errors(Collections.singletonList(error))
                .build();

        return new ResponseEntity<>(errorMessage, httpStatus);
    }

}
