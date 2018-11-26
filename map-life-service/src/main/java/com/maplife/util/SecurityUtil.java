package com.maplife.util;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
public class SecurityUtil {
    private static final Logger logger = Logger.getLogger(SecurityUtil.class);
    private SecurityUtil(){}

    public static String encryptMD5(String content){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符 
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值 
            String md5=new BigInteger(1, digest.digest()).toString(16); 
            //BigInteger会把0省略掉，需补全至32位 
            return fillMD5(md5);
        } catch (NoSuchAlgorithmException e) {
            logger.warn("加密失败", e);
            return null;
        }
    }

    private static String fillMD5(String md5) {
        return md5.length()==32?md5:fillMD5("0"+md5);
    }

    public static String decryptMD5(String content){
        char[] a = content.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    public static void main(String[] args){
        String key = "4dsadasdassada";
        String result = encryptMD5(key);
        logger.info(decryptMD5(key));
        logger.info(decryptMD5(decryptMD5(key)));
    }
}
