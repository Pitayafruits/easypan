package com.cc.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;


/**
 * 生成验证码工具类
 */
public class StringTools {

    //生成随机数
    public static final String getRandomNumber(Integer count){
        return RandomStringUtils.random(count,false,true);
    }

    //非空校验
    public static boolean isEmpty(String str) {

        if (null == str || "".equals(str) || "null".equals(str) || "\u0000".equals(str)) {
            return true;
        } else if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    //md5加密
    public static String encodeByMd5(String originPassword){
        return isEmpty(originPassword) ? null : DigestUtils.md5Hex(originPassword);
    }
}
