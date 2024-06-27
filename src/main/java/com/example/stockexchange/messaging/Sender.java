package com.example.stockexchange.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Sender {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topicName, Object message) {
        kafkaTemplate.send(topicName, message);
    }
}
