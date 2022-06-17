package com.znv.demo.common.utils;

import org.springframework.util.ObjectUtils;

public class CacheUtil {

    public static final String cacheKeyPrefix = "cache:";

    public static final String cacheCountZSetKey = "cache:"+"keyHitCount";

    // 生成缓存计数Key
    public static String getCacheCountZSetKey(String clazzName, String methodName, Object[] args) {
        String zSetKey = clazzName + "-" + methodName;
        for (Object o : args) {
            if (!ObjectUtils.isEmpty(o)) {
                zSetKey += "-" + o.toString();
            }
        }

        return zSetKey;
    }

    // 生成缓存真实存储数据的Key
    public static String getCacheKey(String clazzName, String methodName, Object[] args) {
        String key = "cache:" + clazzName + ":" + methodName;
        for (Object o : args) {
            key += "-" + o.toString();
        }

        return key;
    }
}
