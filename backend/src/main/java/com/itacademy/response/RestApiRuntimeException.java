package com.itacademy.response;

public class RestApiRuntimeException extends RuntimeException{
    public RestApiRuntimeException(String message) {
        super(message);
    }
}
