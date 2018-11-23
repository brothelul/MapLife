package com.maplife.framework.aspect;

import com.maplife.framework.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @auther mosesc
 * @date 11/23/18.
 */
@Aspect
public class LogAspect {

    @Around(value = "log")
    public Object recordLog(ProceedingJoinPoint joinPoint, Log log){

    }
}
