package com.znv.demo.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
// 此切面类主要是用来统计缓存命中次数，以便针对特定方法进行缓存；
/** 由于默认切面类的Order排序为最小值，如果此处不设置排序值，@Cacheable注解本身得切面类会和此类冲突，
     导致不执行此切面类，因此需要在这边特意标注下Order的排序值.
 **/
@Order(-100)
public class CacheCountAspect {

    @Pointcut("@annotation(org.springframework.cache.annotation.Cacheable)")
//    @Pointcut("execution(public * com.znv.demo.service.impl.*ServiceImpl.*(..))")
    public void cacheCountLimit() {

    }

    @Around("cacheCountLimit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();

        String key = signature.getDeclaringTypeName() + "-" + method.getName();
        for (Object o : args) {
            key += "-" + o.toString();
        }

        log.info("CacheCountAspect key: {}", key);

        return point.proceed();
    }
}
