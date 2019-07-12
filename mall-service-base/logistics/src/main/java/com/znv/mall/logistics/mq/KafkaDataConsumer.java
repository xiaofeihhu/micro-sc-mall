package com.znv.mall.logistics.mq;

import com.znv.mall.logistics.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Auther: yf
 * @Date: 2019-6-4
 * @Description:
 */
@Slf4j
@Component
public class KafkaDataConsumer {

    @KafkaListener(containerFactory = "kafkaListenerContainerFactory",
            topics = "${topic.iot.original}")
    public void iotReceive(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        ack.acknowledge();
        if (record == null) {
            return;
        }
        String msg = String.valueOf(record.value());
        log.info("kafka receive:{}",msg);
        WebSocketServer.getWebSocketSet().forEach(a-> {
            try {
                a.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
