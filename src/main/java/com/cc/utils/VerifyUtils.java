package com.cc.utils;


import com.cc.enums.VerifyRegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 校验工具类
 */
public class VerifyUtils {

    public static boolean verify(String regex, String value) {
        if (StringTools.isEmpty(value)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean verify(VerifyRegexEnum regex, String value) {
        return verify(regex.getRegex(), value);
    }

}
