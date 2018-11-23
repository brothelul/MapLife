package com.maplife.framework.aspect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.maplife.entity.SysLog;
import com.maplife.exception.ServiceException;
import com.maplife.framework.annotation.Log;
import com.maplife.service.SysLogService;
import com.maplife.util.StackTraceUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * @auther mosesc
 * @date 11/23/18.
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Around(value = "@annotation(log)")
    public Object recordLog(ProceedingJoinPoint joinPoint, Log log){
        Integer appId = log.app().getSeqNo();
        Integer logType = log.action().getSeqNo();
        String methodName = joinPoint.getTarget().getClass().getName();
        long startTime = System.currentTimeMillis();
        Object data = null;
        String exception = null;
        String exceptionMsg = null;
        String inParams = null;
        try {
            data = joinPoint.proceed();
            return data;
        } catch (Throwable throwable) {
            exception = throwable.getMessage();
            exceptionMsg = StackTraceUtil.stackTrace2String(throwable);
            throw new ServiceException("处理请求失败", throwable);
        } finally {
            long endTime = System.currentTimeMillis();
            SysLog sysLog = new SysLog(null, appId, logType, methodName, null, null, inParams, JSONObject.toJSONString(data), exception, exceptionMsg,null, null, new Date(startTime), new Date(endTime), null, null);
            sysLogService.saveSysLog(sysLog);
        }
    }
}
