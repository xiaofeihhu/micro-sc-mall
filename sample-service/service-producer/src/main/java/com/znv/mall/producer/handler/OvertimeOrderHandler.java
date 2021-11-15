package com.znv.mall.producer.handler;

import com.znv.mall.core.util.ThreadPoolHelper;
import com.znv.mall.producer.common.ServiceConst;
import com.znv.mall.producer.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Auther: yf
 * @Date: 2019-6-27
 * @Description:
 */
@Slf4j
@Component
public class OvertimeOrderHandler {

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    public void dealOvertimeOrder() {

        String orderKeyOvertime = ServiceConst.REDIS_KEY_PREFIX+"overtime:"+"orderIds";

        while (true) {
            // 每次根据超时时间批量取出超时的订单
            Set<Object> reverseRangeByScore =
                    zSetOperations.reverseRangeByScore(orderKeyOvertime, 0, System.currentTimeMillis());
            reverseRangeByScore.forEach(rr->
                    {
                        Long num = zSetOperations.remove(orderKeyOvertime, String.valueOf(rr));
                        if (num!=null && num>0) {
                            ThreadPoolHelper.getInstance().execute(new Runnable() {
                                @Override
                                public void run() {
                                    log.info("deal OvertimeOrderId by redis:{}", (String.valueOf(rr)));
                                }
                            });
                        }
                    }
            );
            // 防止处理过快取出重复数据
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
