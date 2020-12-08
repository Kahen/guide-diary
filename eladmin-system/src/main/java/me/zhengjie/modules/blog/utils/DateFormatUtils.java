package me.zhengjie.modules.blog.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author lijiaxing
 * @date 2020/12/8
 * 格式化时期
 */
@Slf4j
public class DateFormatUtils {

    public static Long formatDate(String date) {
        //MMM dd hh:mm:ss Z yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        try {
            return sdf.parse(date).getTime();
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return 0L;
    }
}
