package com.maplife.exception;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
public class ValidationFailedException extends RuntimeException {
    public ValidationFailedException(){}

    public ValidationFailedException(String message){
        super(message);
    }

    public ValidationFailedException(String message, Throwable throwable){
        super(message, throwable);
    }
}
