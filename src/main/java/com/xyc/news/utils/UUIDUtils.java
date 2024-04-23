package com.xyc.news.utils;

import java.util.UUID;

/**
 *
 * @author xuYuYu
 * @date 2024-04-23 10:12:03
 */
public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getUUID(Integer len) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, len);
    }

}
