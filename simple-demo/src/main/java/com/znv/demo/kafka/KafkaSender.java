package com.znv.demo.kafka;//package com.znv.demo.kafka;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class KafkaSender {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    public void send(String topic, String payload) {
//        log.info("send to topic '{}', payload='{}' ", topic, payload);
//        kafkaTemplate.send(topic, payload);
//    }
//}
