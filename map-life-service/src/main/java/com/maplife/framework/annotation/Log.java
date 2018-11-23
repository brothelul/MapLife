package com.maplife.framework.annotation;

import com.maplife.constant.LogType;
import com.maplife.constant.AppType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther mosesc
 * @date 11/23/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Log {
    AppType app() default AppType.MAP_LIFE;
    LogType action() default LogType.COMMON;
}
