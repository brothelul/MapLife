package com.maplife.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
public class DateUtil {
    private DateUtil(){}

    public static Date getDateAfterAdd(int field, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, amount);
        return calendar.getTime();
    }
}
