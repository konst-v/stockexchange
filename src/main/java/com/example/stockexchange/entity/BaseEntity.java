package com.example.stockexchange.entity;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
}
