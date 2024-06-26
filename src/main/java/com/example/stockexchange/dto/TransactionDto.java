package com.example.stockexchange.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private LocalDateTime dateTime;
    private Integer quantity;
    private BigDecimal price;
}
