package com.znv.demo.common.utils;

public class LogUtil {
    public static String sublog(String logInfo, int length){
        if (logInfo.length() < length) {
            return logInfo;
        }
        return logInfo.substring(0, length) + "...";
    }
}
