package com.olegandreevich.messenger.util;

import com.mongodb.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails details = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorDetails details = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDetails> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        ErrorDetails details = new ErrorDetails(new Date(), ex.getReason(), request.getDescription(false));
        return new ResponseEntity<>(details, ex.getStatusCode());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateKeyException(DuplicateKeyException ex, WebRequest request) {
        ErrorDetails details = new ErrorDetails(new Date(), "Duplicate key error", request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }
}
