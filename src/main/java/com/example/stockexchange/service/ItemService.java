package com.example.stockexchange.service;

import com.example.stockexchange.entity.Item;
import com.example.stockexchange.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.stockexchange.constants.ItemStatus.SOLD;
import static java.lang.StringTemplate.STR;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

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

        if(item.getName() != null) {
            foundItem.setName(item.getName());
        }
        if (item.getPrice() != null) {
            foundItem.setPrice(item.getPrice());
        }
        if (item.getQuantity() != null) {
            foundItem.setQuantity(item.getQuantity());
        }
        return itemRepository.save(foundItem);
    }

    public Item sellItem(Long id) {
        Item foundItem = getItemById(id);
        checkSold(foundItem);
        foundItem.setStatus(SOLD);
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
