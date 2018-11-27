package com.maplife.util;

import com.maplife.constant.ContentType;
import com.maplife.exception.ResourceNotFoundException;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
public class ContentTypeConvertUtil {
    private ContentTypeConvertUtil(){}

    public static String typeNo2TypeTxt(int typeNo){
        ContentType[] types = ContentType.values();
        for (int i = 0; i < types.length; i++) {
            ContentType contentType = types[i];
            if (typeNo == contentType.getSeqNo()){
                return contentType.getName();
            }
        }
        return ContentType.TEXT.getName();
    }

    public static int typeTxt2TypeNo(String typeTxt){
        ContentType[] types = ContentType.values();
        for (int i = 0; i < types.length; i++) {
            ContentType contentType = types[i];
            if (contentType.getName().equals(typeTxt)){
                return contentType.getSeqNo();
            }
        }
        return ContentType.TEXT.getSeqNo();
    }
}
