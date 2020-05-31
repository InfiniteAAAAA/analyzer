package com.example.analyzer.exception;


public class ParameterValidateException extends RuntimeException {

    public ParameterValidateException(String message) {
        super(message);
    }

    public ParameterValidateException(String message, Throwable cause) {
        super(message, cause);
    }
}
