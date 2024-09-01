package com.wliafe.common.utils;

import java.util.UUID;
/**
 * UUID工具
 *
 * @author wliafe
 * @date 2023/1/12 0:18
 **/
public class UUIDUtil {
    /**
     * @return 32位UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * @param num UUID位数<32
     * @return num位UUID
     */
    public static String getUUID(Integer num) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(32 - num);
    }
}
