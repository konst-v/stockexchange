package com.example.stockexchange.messaging;

import com.example.stockexchange.dto.TransactionDto;
import com.example.stockexchange.mapper.TransactionMapper;
import com.example.stockexchange.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.stockexchange.constants.KafkaTopics.TRANSACTION_TOPIC;

@Component
@Slf4j
@AllArgsConstructor
public class Receiver {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @KafkaListener(topics = TRANSACTION_TOPIC)
    public void processMessage(TransactionDto message) {
        transactionService.logTransaction(transactionMapper.mapToEntity(message));
    }
}