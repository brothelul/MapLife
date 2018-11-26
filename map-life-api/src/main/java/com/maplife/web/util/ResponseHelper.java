package com.maplife.web.util;

public class ResponseHelper {
    public static <T> JsonEntity<T> createInstance(T object){
        return new JsonEntity(object);
    }

    public static <T> JsonEntity<T> createMessageInstance(String message){
        return new JsonEntity<T>(message);
    }
}
