package com.zjj.aisearch.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: AISearch
 * @description: 日期时间转换
 * @author: zjj
 * @create: 2019-09-07 23:43:05
 **/
public class DateConvert {
    public static String getTime() {

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
