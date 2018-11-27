package com.maplife.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
public class JSONUtil {
    private JSONUtil(){}

    public static String object2JsonString(Object object){
        if (object == null){
            return null;
        }
        return JSONObject.toJSONString(object);
    }
}
