package com.example.stockexchange.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.example.stockexchange.constants.KafkaTopics.TRANSACTION_TOPIC;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name(TRANSACTION_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
