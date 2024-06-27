package com.example.stockexchange.dto;

import com.example.stockexchange.constants.ItemStatus;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long id;

    private String name;

    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;

    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;

    private ItemStatus status;
}
