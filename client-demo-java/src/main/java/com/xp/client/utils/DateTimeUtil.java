package com.xp.client.utils;

import cn.hutool.core.date.DatePattern;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class DateTimeUtil {

    public static String getDateYMD() {
        DateFormat dateFormat = new SimpleDateFormat(DatePattern.PURE_DATE_PATTERN);
        return dateFormat.format(new Date());
    }

    public static String getDateTimeYMDHMS() {
        DateFormat dateFormat = new SimpleDateFormat(DatePattern.PURE_DATETIME_PATTERN);
        return dateFormat.format(new Date());
    }

    public static String format(Date date, String format) {
        if (date == null || StringUtils.isBlank(format)) {
            return "";
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        } catch (Exception e) {
            return "";
        }
    }
}
