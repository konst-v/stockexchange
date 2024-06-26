package com.example.stockexchange.messaging;

import com.example.stockexchange.dto.TransactionDto;
import com.example.stockexchange.mapper.TransactionMapper;
import com.example.stockexchange.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Receiver {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @KafkaListener(topics = "${topic_name:notify-topic}")
    public void processMessage(Object message) {
        Object value = ((ConsumerRecord) message).value();
        switch (value) {
            case TransactionDto dto:
                transactionService.logTransaction(transactionMapper.mapToEntity(dto));
            default:
                log.warn(STR."Unknown message received: \{value}");
        }
    }
}