package com.example.kafkademo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListener {

    @KafkaListener(topics = "test-topic", groupId = "kafkademo-group")
    public void listen(String message) {
        System.out.println("🔥 Consumed message: " + message);
    }
}
