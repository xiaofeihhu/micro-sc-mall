package com.znv.mall.producer.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auther: yf
 * @Date: 2019/9/25
 * @Description:
 */

@JobHandler("xxlJobTask1")
@Component
@Slf4j
public class XxlJobTask1 extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("mall xxl-job xxlJobTask1!-xxl log");
        log.info("mall xxl-job xxlJobTask1!-slf4j log");

        return SUCCESS;
    }
}
