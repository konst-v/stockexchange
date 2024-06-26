package com.example.stockexchange.service;

import com.example.stockexchange.entity.Item;
import com.example.stockexchange.mapper.TransactionMapper;
import com.example.stockexchange.messaging.Sender;
import com.example.stockexchange.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.stockexchange.constants.ItemStatus.SOLD;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final Sender sender;
    private final TransactionMapper transactionMapper;

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(STR."Item id \{id} not found"));
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        Item foundItem = getItemById(item.getId());
        checkSold(foundItem);

        Optional.ofNullable(item.getName())
                .ifPresent(o -> foundItem.setName(o));
        Optional.ofNullable(item.getPrice())
                .ifPresent(o -> foundItem.setPrice(o));
        Optional.ofNullable(item.getQuantity())
                .ifPresent(o -> foundItem.setQuantity(o));

        return itemRepository.save(foundItem);
    }

    @Transactional
    public Item sellItem(Long id) {
        Item foundItem = getItemById(id);
        checkSold(foundItem);
        foundItem.setStatus(SOLD);
        sender.send(transactionMapper.mapItemToDto(foundItem));
        return itemRepository.save(foundItem);
    }

    public void deleteItem(Long id) {
        Item foundItem = getItemById(id);
        checkSold(foundItem);
        itemRepository.delete(foundItem);
    }

    private static void checkSold(Item item) {
        if (item.getStatus() == SOLD) {
            throw new IllegalArgumentException(STR."No operations allowed on SOLD item \{item.getId()}");
        }
    }
}
