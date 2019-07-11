package com.znv.mall.producer.business.delay;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: yf
 * @Date: 2019-6-28
 * @Description:
 */
@Slf4j
public class OvertimeTask implements Task {

    private String orderId;

    public OvertimeTask(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void executeTask() {
        log.info("deal OvertimeOrderId by DelayQueue:{}",orderId);
    }
}
