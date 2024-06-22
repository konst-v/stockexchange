package com.example.stockexchange.dto;

import com.example.stockexchange.constants.ItemStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemCreationDto {
    @NotNull(message = "Name is missing")
    private String name;

    @NotNull(message = "Price is missing")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Quantity is missing")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;
}