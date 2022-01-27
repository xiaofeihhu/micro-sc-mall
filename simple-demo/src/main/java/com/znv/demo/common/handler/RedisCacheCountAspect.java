//package com.znv.demo.common.handler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
//@Slf4j
//@Aspect
//@Component
////@Order(-100)
//public class RedisCacheCountAspect {
//
//    @Pointcut("execution(public * com.znv.demo.service.impl.*ServiceImpl.*(..))")
//    public void cacheCountLimit() {
//
//    }
//
//    @Around("cacheCountLimit()")
//    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        Method method = signature.getMethod();
//        Object[] args = point.getArgs();
//
//        String key = signature.getDeclaringTypeName() + "-" + method.getName();
//        for (Object o : args) {
//            key += "-" + o.toString();
//        }
//
//        log.info("RedisCacheCountAspect key: {}", key);
//
//        return point.proceed();
//    }
//}
