package com.cc.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 生成验证码工具类
 */
public class StringTools {

    //生成随机数
    public static final String getRandomNumber(Integer count){
        return RandomStringUtils.random(count,false,true);
    }


}
