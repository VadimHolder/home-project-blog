package com.itacademy.response;

public class RestApiForbiddenException extends RuntimeException {
    public RestApiForbiddenException(String message) {
        super(message);
    }
}
