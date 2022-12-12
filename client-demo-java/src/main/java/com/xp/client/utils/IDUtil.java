package com.xp.client.utils;

import java.util.UUID;

/**
 * id生成工具类
 */
public class IDUtil {

    public static String generateShortUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
