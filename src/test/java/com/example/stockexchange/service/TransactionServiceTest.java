package com.example.stockexchange.service;

import com.example.stockexchange.entity.Transaction;
import com.example.stockexchange.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void shouldSave() {
        Transaction transaction = new Transaction();
        transactionService.logTransaction(transaction);
        verify(transactionRepository).save(transaction);
    }
}
