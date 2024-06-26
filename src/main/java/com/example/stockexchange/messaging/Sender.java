package com.example.stockexchange.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Sender {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topic_name:notify-topic}")
    private String topic;

    public void send(Object message) {
        kafkaTemplate.send(topic, message);
    }
}
