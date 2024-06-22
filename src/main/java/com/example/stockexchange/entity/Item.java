package com.example.stockexchange.entity;

import com.example.stockexchange.constants.ItemStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Item extends BaseEntity {
    private String name;

    private BigDecimal price;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;
}
