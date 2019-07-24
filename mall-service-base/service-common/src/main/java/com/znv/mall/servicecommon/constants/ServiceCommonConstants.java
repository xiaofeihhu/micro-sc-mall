package com.znv.mall.servicecommon.constants;

public class ServiceCommonConstants {

    // redis key
    public final static String REDIS_KEY_LOGIN_FAIL_COUNT = "mall:login:fail:count:";
    public final static String REDIS_KEY_REGISTER_AUTHCODE_PREFIX = "mall:register:authcode:";

    public final static int REDIS_KEY_REGISTER_AUTHCODE_EXPIRE = 5*60;
}
