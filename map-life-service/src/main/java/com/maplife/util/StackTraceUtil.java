package com.maplife.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @auther mosesc
 * @date 11/23/18.
 */
public class StackTraceUtil {
    private StackTraceUtil(){}

    public static String stackTrace2String(Throwable throwable){
        StringWriter sw = new StringWriter();
        try(PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
        }
        return sw.toString();
    }
}
