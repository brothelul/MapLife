package com.maplife.web.framework.exception;

public class CannotCaseException extends RuntimeException {
    public CannotCaseException(){}

    public CannotCaseException(String message){
        super(message);
    }

    public CannotCaseException(String message, Throwable throwable){
        super(message, throwable);
    }
}
