package com.znv.mall.producer.task;

import com.znv.mall.producer.business.delay.MyDelayedEvent;
import com.znv.mall.producer.business.delay.MyDelayedService;
import com.znv.mall.producer.business.delay.OvertimeTask;
import com.znv.mall.producer.common.ServiceConst;
import com.znv.mall.producer.util.UniqueIdGeneratorUtil;
import com.znv.mall.producer.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: yf
 * @Date: 2019-6-25
 * @Description:
 */
@Component
@Slf4j
public class ScheduledTask {

    private AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Autowired
    MyDelayedService myDelayedService;

    @Scheduled(cron = "0 0 1 * * ? ")
    @Async
    public void task1() {
        log.info("ScheduledTask running...");
    }

    @Scheduled(cron = "0 0/2 * * * ? ")
    @Async
    public void createOrderIdTask() {
        log.info("createOrderIdTask running...");

        // 存储订单id列表到redis中
        String orderKey = ServiceConst.REDIS_KEY_PREFIX+"orderIds";
        List<String> orderIdList = (List<String>) RedisUtil.lGetIndex(orderKey,0);
        if (orderIdList==null||orderIdList.size()==0) {
            orderIdList = new ArrayList<>();
        }
        String orderId = UniqueIdGeneratorUtil.createUniqueIdIdByRedisInc();
        orderIdList.add(orderId);
        RedisUtil.lSet(orderKey,orderIdList,3600);

        // 用redis生成超时订单
        String orderKeyOvertime = ServiceConst.REDIS_KEY_PREFIX+"overtime:"+"orderIds";
        zSetOperations.add(orderKeyOvertime,orderId,System.currentTimeMillis()+1000*60);
        RedisUtil.expire(orderKeyOvertime,3600);

        // 用DelayQueue生成超时订单
        MyDelayedEvent delayed_order = new MyDelayedEvent(new OvertimeTask(orderId),System.currentTimeMillis()+1000*60);
        myDelayedService.put(delayed_order);

    }
}
