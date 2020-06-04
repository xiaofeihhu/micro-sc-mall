package com.znv.demo.common.utils;

public class ThreadLocalUtil {

    public static final ThreadLocal<Long> requestIdThreadLocal = new ThreadLocal<Long>();

    public static void set(long requestId) {
        requestIdThreadLocal.set(requestId);
    }

    public static long get() {
        if (requestIdThreadLocal.get() == null) {
            return 0L;
        }
        return requestIdThreadLocal.get();
    }
}
