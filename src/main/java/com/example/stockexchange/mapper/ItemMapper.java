package com.example.stockexchange.mapper;

import com.example.stockexchange.dto.ItemCreationDto;
import com.example.stockexchange.dto.ItemDto;
import com.example.stockexchange.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "status", ignore = true)
    Item mapToEntity(ItemDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "AVAILABLE")
    Item mapToEntity(ItemCreationDto dto);

    ItemDto mapToDto(Item entity);
}
