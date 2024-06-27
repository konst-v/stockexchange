package com.example.stockexchange.messaging;

import com.example.stockexchange.dto.TransactionDto;
import com.example.stockexchange.entity.Transaction;
import com.example.stockexchange.mapper.TransactionMapper;
import com.example.stockexchange.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static com.example.stockexchange.constants.KafkaTopics.TRANSACTION_TOPIC;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReceiverTest {
    @Mock
    private TransactionService transactionService;
    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private Receiver receiver;

    private TransactionDto dto;
    private Transaction transaction;

    @BeforeEach
    void init() {
        dto = new TransactionDto();
        transaction = new Transaction();
    }

    @Test
    void shouldReceiveAndSave() {
        when(transactionMapper.mapToEntity(dto)).thenReturn(transaction);
        receiver.processMessage(dto);
        verify(transactionMapper).mapToEntity(dto);
        verify(transactionService).logTransaction(transaction);
    }
}
