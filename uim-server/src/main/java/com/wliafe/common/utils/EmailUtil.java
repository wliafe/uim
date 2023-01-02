package com.wliafe.common.utils;

import java.util.regex.Pattern;

public class EmailUtil {
    public static boolean checkEmail(String email) {
        Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        return regex.matcher(email).matches();
    }
}
