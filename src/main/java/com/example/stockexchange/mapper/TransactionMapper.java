package com.example.stockexchange.mapper;

import com.example.stockexchange.dto.TransactionDto;
import com.example.stockexchange.entity.Item;
import com.example.stockexchange.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface TransactionMapper {

    Transaction mapToEntity(TransactionDto dto);

    @Mapping(target = "dateTime", expression = "java(LocalDateTime.now())")
    TransactionDto mapItemToDto(Item entity);
}
