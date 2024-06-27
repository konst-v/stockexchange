package com.example.stockexchange.service;

import com.example.stockexchange.dto.TransactionDto;
import com.example.stockexchange.entity.Item;
import com.example.stockexchange.mapper.TransactionMapper;
import com.example.stockexchange.messaging.Sender;
import com.example.stockexchange.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.stockexchange.constants.ItemStatus.AVAILABLE;
import static com.example.stockexchange.constants.ItemStatus.SOLD;
import static com.example.stockexchange.constants.KafkaTopics.TRANSACTION_TOPIC;
import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private TransactionMapper transactionMapper;
    @Mock
    private Sender sender;

    @InjectMocks
    private ItemService itemService;

    private Item item1, item2;

    @BeforeEach
    void init() {
        item1 = new Item();
        item1.setId(1L);
        item1.setName("test1");
        item1.setPrice(BigDecimal.valueOf(11.12));
        item1.setQuantity(1);
        item1.setStatus(AVAILABLE);
        item2 = new Item();
        item2.setId(1L);
        item2.setName("test2");
        item2.setPrice(BigDecimal.valueOf(22.23));
        item2.setQuantity(2);
        item2.setStatus(AVAILABLE);
    }

    @Test
    void shouldFindById_success() {
        when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(item1));
        Item result = itemService.getItemById(1L);
        verify(itemRepository).findById(1L);
        assertEquals(result, item1);
    }

    @Test
    void shouldFindById_notFound() {
        when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> itemService.getItemById(1L));
        verify(itemRepository).findById(1L);
        assertEquals("Item id 1 not found", ex.getMessage());
    }

    @Test
    void shouldModify_success() {
        when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(item1));
        when(itemRepository.save(item1)).thenReturn(item1);
        Item result = itemService.updateItem(item2);
        
        verify(itemRepository).findById(1L);
        verify(itemRepository).save(item1);
        assertEquals(result.getName(), item2.getName());
        assertEquals(result.getQuantity(), item2.getQuantity());
        assertEquals(result.getPrice(), item2.getPrice());
    }

    @Test
    void shouldSell_success() {
        TransactionDto dto = new TransactionDto();
        when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(item1));
        when(itemRepository.save(item1)).thenReturn(item1);
        when(transactionMapper.mapItemToDto(item1)).thenReturn(dto);
        Item result = itemService.sellItem(item1.getId());

        verify(itemRepository).findById(1L);
        verify(transactionMapper).mapItemToDto(item1);
        verify(sender).send(TRANSACTION_TOPIC, dto);
        verify(itemRepository).save(item1);
        assertEquals(result.getStatus(), SOLD);
    }

    @Test
    void shouldSell_errorAlreadySold() {
        item1.setStatus(SOLD);
        when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(item1));
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> itemService.sellItem(item1.getId()));

        verify(itemRepository).findById(1L);
        verifyNoInteractions(transactionMapper);
        verifyNoInteractions(sender);
        assertEquals("No operations allowed on SOLD item 1", ex.getMessage());
    }

    @Test
    void shouldDelete_success() {
        when(itemRepository.findById(1L)).thenReturn(Optional.ofNullable(item1));
        itemService.deleteItem(item1.getId());

        verify(itemRepository).findById(1L);
        verify(itemRepository).delete(item1);
    }
}
