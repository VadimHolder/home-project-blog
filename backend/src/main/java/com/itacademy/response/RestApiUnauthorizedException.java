package com.itacademy.response;

public class RestApiUnauthorizedException extends RuntimeException{
    public RestApiUnauthorizedException(String message) {
        super(message);
    }
}
