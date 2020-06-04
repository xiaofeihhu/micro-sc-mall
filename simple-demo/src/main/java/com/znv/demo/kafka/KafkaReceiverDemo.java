package com.znv.demo.kafka;//package com.znv.demo.kafka;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CountDownLatch;
//
//@Slf4j
//@Data
////@Component
//public class KafkaReceiverDemo {
//
//    private CountDownLatch latch = new CountDownLatch(1);
//
//    @KafkaListener(topics = "test")
//    public void listen(String payload) throws Exception {
//        log.info(payload.toString());
//        latch.countDown();
//    }
//}
