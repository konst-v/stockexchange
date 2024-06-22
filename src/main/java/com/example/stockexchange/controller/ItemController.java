package com.example.stockexchange.controller;

import com.example.stockexchange.dto.ItemCreationDto;
import com.example.stockexchange.dto.ItemDto;
import com.example.stockexchange.mapper.ItemMapper;
import com.example.stockexchange.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestBody @Valid ItemCreationDto creationDto) {
        return itemMapper.mapToDto(itemService.createItem(itemMapper.mapToEntity(creationDto)));
    }

    @PatchMapping
    public ItemDto updateItem(@RequestBody @Valid ItemDto dto) {
        return itemMapper.mapToDto(itemService.updateItem(itemMapper.mapToEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    @PostMapping("/sell/{id}")
    public ItemDto sellItem(@PathVariable Long id) {
        return itemMapper.mapToDto(itemService.sellItem(id));
    }
}
