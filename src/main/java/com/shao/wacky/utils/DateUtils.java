package com.shao.wacky.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 时间工具类
 */
public class DateUtils {

    public final static String TIME_FORMAT_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 返回当前时间
     * @param format
     * @return
     */
    public static String currentTime(String format){
        SimpleDateFormat sdf = new SimpleDateFormat("",Locale.CHINESE);
        sdf.applyPattern(format);
        return sdf.format(System.currentTimeMillis());
    }
}
