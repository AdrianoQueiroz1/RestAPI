package com.capgemini.springboot.controllers;

import com.capgemini.springboot.entities.Item;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private List<Item> itemList = new ArrayList<>();
    @PostConstruct
    public void init() {
        // Add some dummy data to the itemList
        itemList.add(new Item(1L, "Item 1", 9.99));
        itemList.add(new Item(2L, "Item 2", 19.99));
        itemList.add(new Item(3L, "Item 3", 14.49));
    }

    // Endpoint to get all items
    @GetMapping
    public List<Item> getItems() {
        return itemList;
    }

    // Endpoint to get a specific item by ID
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Endpoint to create a new item
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        itemList.add(item);
        return item;
    }

    // Endpoint to update an existing item
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        Item existingItem = itemList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setName(updatedItem.getName());
            existingItem.setPrice(updatedItem.getPrice());
        }

        return existingItem;
    }

    // Endpoint to delete an item by ID
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemList.removeIf(item -> item.getId().equals(id));
    }
}