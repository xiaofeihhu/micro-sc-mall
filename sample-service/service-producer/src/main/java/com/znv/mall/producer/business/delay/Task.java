package com.znv.mall.producer.business.delay;

/**
 * @Auther: yf
 * @Date: 2019-6-28
 * @Description:
 */
public interface Task {

    //调用该方法，则会执行任务
     public void executeTask();
}
