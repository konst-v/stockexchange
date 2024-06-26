package com.example.stockexchange.service;

import com.example.stockexchange.entity.Transaction;
import com.example.stockexchange.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public void logTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
