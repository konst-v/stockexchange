package com.example.stockexchange.entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction extends BaseEntity {
    private LocalDateTime dateTime;
    private Integer quantity;
    private BigDecimal price;
}
