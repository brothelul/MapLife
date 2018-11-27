package com.maplife.admin.framework.aspect;

import com.maplife.constant.SystemConstant;
import com.maplife.entity.SysLog;
import com.maplife.entity.User;
import com.maplife.exception.ServiceException;
import com.maplife.service.SysLogService;
import com.maplife.util.JSONUtil;
import com.maplife.util.StackTraceUtil;
import com.maplife.web.framework.annotation.Log;
import com.maplife.web.framework.exception.CannotCaseException;
import com.maplife.web.session.WebContext;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther mosesc
 * @date 11/23/18.
 */
@Aspect
@Component
public class LogAspect {
    private static Logger logger = Logger.getLogger(LogAspect.class);
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private WebContext webContext;

    @Around(value = "@annotation(log)")
    public Object recordLog(ProceedingJoinPoint joinPoint, Log log){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String methodName = joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName();
        String sessionId = request.getSession().getId();
        String requestUrl = request.getMethod()+" "+request.getRequestURI();
        String ipAddress = request.getRemoteHost();
        Integer appId = log.app().getSeqNo();
        Integer logType = log.action().getSeqNo();
        long startTime = System.currentTimeMillis();
        User user = webContext.getCurrentUser();
        Integer userId = user == null ? null : user.getUserId();
        Object data = null;
        String exception = null;
        String exceptionMsg = null;
        // 初始化入参
        Object[] params = joinPoint.getArgs();
        Map<String, Object> inParamsMap = null;
        if (params != null && params.length > 0){
            inParamsMap = new HashMap<String, Object>(4);
            for (int i = 0; i < params.length; i++){
                Object object = params[i];
                String key = "arg" + i;
                try {
                    if (object instanceof MultipartFile){
                        throw new CannotCaseException("文件对象不进行转换");
                    }
                    if (object instanceof  HttpServletRequest){
                        throw new CannotCaseException("Http请求不进行转换");
                    }
                    inParamsMap.put(key, object);
                } catch (Exception e){
                    logger.warn("转化对象"+params[i]+"成JSON字符串失败", e);
                    inParamsMap.put(key, object.getClass().getName());
                }
            }
        }
        try {
            data = joinPoint.proceed();
            return data;
        } catch (Throwable throwable) {
            exception = throwable.getMessage();
            exceptionMsg = StackTraceUtil.stackTrace2String(throwable);
            throw new ServiceException("处理请求失败", throwable);
        } finally {
            long endTime = System.currentTimeMillis();
            SysLog sysLog = new SysLog(null, appId, logType, methodName, userId, requestUrl, JSONUtil.object2JsonString(inParamsMap), JSONUtil.object2JsonString(data), exception, exceptionMsg,sessionId, ipAddress, new Date(startTime), new Date(endTime), SystemConstant.SYSTEM_ID, new Date());
            sysLogService.saveSysLog(sysLog);
        }
    }
}
