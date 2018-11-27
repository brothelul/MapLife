package com.maplife.exception;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){}

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
