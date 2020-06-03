package com.znv.mall.consumer.jwt;

import java.lang.annotation.*;

/**
 * @Auther: yf
 * @Date: 2020/6/2
 * @Description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}
