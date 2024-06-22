package com.example.stockexchange.repository;

import com.example.stockexchange.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
