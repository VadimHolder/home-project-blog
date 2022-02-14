package com.itacademy.response;


public class RestApiNotFoundException extends RuntimeException {

    public RestApiNotFoundException(String s) {
        super(s);
    }
}
