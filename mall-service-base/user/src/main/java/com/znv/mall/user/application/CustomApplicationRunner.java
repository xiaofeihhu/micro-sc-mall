package com.znv.mall.user.application;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 程序启动成功后在这边干些事情，比如创建连接啥的
 */
@Component
@Order(6)
@Slf4j
public class CustomApplicationRunner implements ApplicationRunner {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("{} 启动成功！！",applicationName);
    }

}
