//package com.znv.mall.producer.mq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
///**
// * @Auther: yf
// * @Date: 2019-6-4
// * @Description:
// */
//@Component
//@Slf4j
//public class KafkaDataProducer {
//
//    @Autowired
//    KafkaTemplate kafkaTemplate;
//
//    public void send(String topic,String msg) {
//        kafkaTemplate.send(topic,StringUtils.isEmpty(msg) ? "test message" : msg);
//        log.info("kafka send success!,topic:{}, msg:{}",topic,msg);
//    }
//}
