//package com.znv.demo.application;
//
//import com.alibaba.csp.sentinel.datasource.Converter;
//import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
//import com.alibaba.csp.sentinel.datasource.redis.RedisDataSource;
//import com.alibaba.csp.sentinel.datasource.redis.config.RedisConnectionConfig;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @Auther: yf
// * @Date: 2020/6/3
// * @Description:
// */
//@Component
//@Order(2)
//@Slf4j
//public class RedisSentinelRule implements CommandLineRunner{
//    @Override
//    public void run(String... args) throws Exception {
//        String redisHost = "10.45.157.182";
//        int redisPort = 6379;
//        String pass = "zxm10@@@";
//        String ruleKey = "demo";
//        String channel = "test";
//        Converter<String, List<FlowRule>> parser = source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>() {});
//        RedisConnectionConfig config = RedisConnectionConfig.builder()
//                .withHost(redisHost)
//                .withPort(redisPort)
////                .withPassword(pass)
//                .build();
//        ReadableDataSource<String, List<FlowRule>> redisDataSource = new RedisDataSource<>(config, ruleKey, channel, parser);
//        FlowRuleManager.register2Property(redisDataSource.getProperty());
//    }
//}
