package com.znv.demo.common.handler;

import com.znv.demo.common.utils.CacheUtil;
import com.znv.demo.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static com.znv.demo.common.utils.CacheUtil.cacheCountZSetKey;

@Slf4j
@Aspect
@Component
// 此切面类主要是用来统计缓存命中次数，以便针对特定方法进行缓存；
/** 由于默认切面类的Order排序为最小值，如果此处不设置排序值，@Cacheable注解本身得切面类会和此类冲突，
     导致不执行此切面类，因此需要在这边特意标注下Order的排序值.
 **/
@Order(-100)
public class CacheCountAspect {



    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

//    @Pointcut("@annotation(org.springframework.cache.annotation.Cacheable)")
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//    @Pointcut("execution(public * com.znv.demo.service.impl.*ServiceImpl.*(..))")
    public void cacheCountLimit() {

    }

    @Around("cacheCountLimit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();

        String zSetKey = CacheUtil.getCacheCountZSetKey(signature.getDeclaringTypeName(), method.getName(), args);
        String dataKey = CacheUtil.getCacheKey(signature.getDeclaringTypeName(), method.getName(), args);
        log.info("CacheCountAspect zSetKey: {}, dataKey: {}", zSetKey, dataKey);
        // 记录该接口参数被调用的次数，利用zset存储到redis，提供接口查询次数，手动判断调用次数的阈值大于多少次进行缓存处理，而不是全部的接口进行缓存，避免影响缓存性能

        int count;
//        if (RedisUtil.hHasKey(cacheCountKey,key)) {
//            count = (int)RedisUtil.hget(cacheCountKey,key)+1;
//        } else {
//            count = 1;
//        }
//        RedisUtil.hset(cacheCountKey,key,count,3600);

        Double hitCount = RedisUtil.zScore(cacheCountZSetKey,zSetKey);
        if (hitCount == null) {
            RedisUtil.zAdd(cacheCountZSetKey,zSetKey,1);
            RedisUtil.expire(cacheCountZSetKey,3600);
        } else {
            RedisUtil.zAdd(cacheCountZSetKey,zSetKey,hitCount+1);
            RedisUtil.expire(cacheCountZSetKey,3600);
            // 缓存命中了2次以上，证明是常用接口，开始走缓存
            if (hitCount >= 2) {
                return RedisUtil.get(dataKey);
            }
        }

        Object result = point.proceed();

        if (hitCount != null && hitCount == 1) {
            RedisUtil.set(dataKey, result,5*60);
        }
        return result;
    }
}
