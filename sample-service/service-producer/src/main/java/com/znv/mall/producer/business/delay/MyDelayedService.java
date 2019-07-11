package com.znv.mall.producer.business.delay;

/**
 * @Auther: yf
 * @Date: 2019-6-28
 * @Description:
 */
public interface MyDelayedService {
    //插入任务调度
    void put(MyDelayedEvent delayed);
    //移除任务调度
    boolean remove(MyDelayedEvent delayed);
    //初始化该任务调度
    void init();
}
