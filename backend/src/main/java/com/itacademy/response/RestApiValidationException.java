package com.itacademy.response;

import com.itacademy.exceptions.ValidationException;

public class RestApiValidationException extends ValidationException {
    public RestApiValidationException(String message) {
        super(message);
    }
}
