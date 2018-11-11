package com.wxl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期和字符串转换工具类
 */
public class DateUtils {

    /**
     * 时间转换为字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String DateToString(Date date,String pattern) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转为时间对象
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String date,String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }
}
