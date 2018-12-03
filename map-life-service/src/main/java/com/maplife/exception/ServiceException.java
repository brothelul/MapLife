package com.maplife.exception;

/**
 * @auther mosesc
 * @date 11/23/18.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(){}

    public ServiceException(Throwable throwable){
        super(throwable);
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable throwable){
        super(message, throwable);
    }
}
