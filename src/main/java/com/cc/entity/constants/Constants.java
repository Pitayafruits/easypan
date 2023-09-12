package com.cc.entity.constants;

/**
 * 常量 状态量
 */
public class Constants {

    //登录注册
    public static final String CHECK_CODE_KEY = "check_code_key";

    //邮箱验证码发送
    public static final String CHECK_CODE_KEY_EMAIL = "check_code_key_email";

    public static final Integer LENGTH_5 = 5;

    public static final Integer LENGTH_10 = 10;

    public static final Integer LENGTH_15 = 15;

    public static final Integer ZERO = 0;

    public static final Long MB = 1024 * 1024L;

    public static final String SESSION_KEY = "session_key";

    public static final Integer REDIS_KEY_EXPIRES_ONE_MIN = 60;
    //redis key
    public static final Integer REDIS_KEY_EXPIRES_DAY = REDIS_KEY_EXPIRES_ONE_MIN * 60 * 24;

    public static final String REDIS_KEY_SYS_SETTING = "easypan:syssetting:";

    public static final String REDIS_KEY_USER_SPACE_USE = "easypan:user:space:use:";

}
