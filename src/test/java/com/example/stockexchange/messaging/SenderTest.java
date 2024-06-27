package com.example.stockexchange.messaging;

import com.example.stockexchange.entity.Transaction;
import com.example.stockexchange.repository.TransactionRepository;
import com.example.stockexchange.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static com.example.stockexchange.constants.KafkaTopics.TRANSACTION_TOPIC;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SenderTest {
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private Sender sender;

    @Test
    void shouldSend() {
        String message = "test message";
        sender.send(TRANSACTION_TOPIC, message);
        verify(kafkaTemplate).send(TRANSACTION_TOPIC, message);
    }
}
