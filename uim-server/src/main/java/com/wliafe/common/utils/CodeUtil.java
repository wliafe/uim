package com.wliafe.common.utils;

public class CodeUtil {
    public static String randomCode() {
        return Integer.valueOf((int) (Math.random() * 9000 + 1000)).toString();
    }
}
