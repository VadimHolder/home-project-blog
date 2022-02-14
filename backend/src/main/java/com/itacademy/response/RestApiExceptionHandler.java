package com.itacademy.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler {


    @ExceptionHandler(value = {RestApiValidationException.class})
    public ResponseEntity<Error> validation(RestApiValidationException e) {
        Error error = new Error(400, e.getMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(value = {RestApiUnauthorizedException.class})
    public ResponseEntity<Error> unauthorized(RestApiUnauthorizedException e) {
        Error error = new Error(401, e.getMessage());
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(value = {RestApiForbiddenException.class})
    public ResponseEntity<Error> forbidden(RestApiForbiddenException e) {
        Error error = new Error(403, e.getMessage());
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(value = {RestApiNotFoundException.class})
    public ResponseEntity<Error> notFound(RestApiNotFoundException e) {
        Error error = new Error(404, e.getMessage());
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(value = {RestApiRuntimeException.class})
    public ResponseEntity<Error> serverError(Exception e) {
        Error error = new Error(500, e.getMessage());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(error, httpStatus);
    }


}

