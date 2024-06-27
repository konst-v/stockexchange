package com.example.stockexchange.controller;

import com.example.stockexchange.dto.ItemCreationDto;
import com.example.stockexchange.dto.ItemDto;
import com.example.stockexchange.entity.Item;
import com.example.stockexchange.entity.Transaction;
import com.example.stockexchange.mapper.ItemMapper;
import com.example.stockexchange.repository.TransactionRepository;
import com.example.stockexchange.service.ItemService;
import com.example.stockexchange.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.example.stockexchange.constants.ItemStatus.AVAILABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    @Mock
    private ItemService itemService;
    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemController itemController;

    private Item item;
    private ItemDto dto;
    private ItemCreationDto creationDto;

    @BeforeEach
    void init() {
        item = new Item();
        item.setId(1L);
        item.setName("test1");
        item.setPrice(BigDecimal.valueOf(11.12));
        item.setQuantity(1);
        item.setStatus(AVAILABLE);
        dto = new ItemDto();
        dto.setName("test1");
        dto.setPrice(BigDecimal.valueOf(11.12));
        dto.setQuantity(2);
        creationDto = new ItemCreationDto();
        creationDto.setName("test1");
        creationDto.setPrice(BigDecimal.valueOf(11.12));
        creationDto.setQuantity(1);
    }

    @Test
    void shouldCreateItem() {
        when(itemMapper.mapToEntity(creationDto)).thenReturn(item);
        when(itemService.createItem(item)).thenReturn(item);
        when(itemMapper.mapToDto(item)).thenReturn(dto);

        ItemDto result = itemController.createItem(creationDto);
        verify(itemMapper).mapToEntity(creationDto);
        verify(itemService).createItem(item);
        verify(itemMapper).mapToDto(item);
        assertEquals(result, dto);
    }

    @Test
    void shouldUpdateItem() {
        when(itemMapper.mapToEntity(dto)).thenReturn(item);
        when(itemService.updateItem(item)).thenReturn(item);
        when(itemMapper.mapToDto(item)).thenReturn(dto);

        ItemDto result = itemController.updateItem(dto);
        verify(itemMapper).mapToEntity(dto);
        verify(itemService).updateItem(item);
        verify(itemMapper).mapToDto(item);
        assertEquals(result, dto);
    }

    @Test
    void shouldDeleteItem() {
        doNothing().when(itemService).deleteItem(1L);

        itemController.deleteItem(1L);
        verify(itemService).deleteItem(1L);
    }

    @Test
    void shouldSellItem() {
        when(itemService.sellItem(1L)).thenReturn(item);
        when(itemMapper.mapToDto(item)).thenReturn(dto);

        ItemDto result = itemController.sellItem(1L);
        verify(itemService).sellItem(1L);
        verify(itemMapper).mapToDto(item);
        assertEquals(result, dto);
    }
}
