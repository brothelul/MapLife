package com.maplife.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
public class ContentFilterUtil {

    private static Pattern emojiPattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    private ContentFilterUtil(){}

    public static String EmojiFilter(String content){
        if (StringUtils.isEmpty(content)){
            return content;
        }
        Matcher matcher = emojiPattern.matcher(content);
        if (matcher.find()){
            content = matcher.replaceAll("-");
        }
        return content;
    }
}
