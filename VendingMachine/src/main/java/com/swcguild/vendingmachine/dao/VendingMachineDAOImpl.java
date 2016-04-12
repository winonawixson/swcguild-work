/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachine.dao;

import com.swcguild.vendingmachine.model.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author apprentice
 */
@Repository
public class VendingMachineDAOImpl implements VendingMachineDAO {

    private Map<Integer, Item> allItems = new HashMap<>();
    private int itemIdCounter = 6;

    public VendingMachineDAOImpl() {
        Item item = new Item();
        item.setItemName("Water");
        item.setItemPrice(1.25);
        item.setItemQuantity(3);
        item.setItemId(1);
        allItems.put(item.getItemId(), item);

        item = new Item();
        item.setItemName("Snickers");
        item.setItemPrice(1.25);
        item.setItemQuantity(10);
        item.setItemId(2);
        allItems.put(item.getItemId(), item);

        item = new Item();
        item.setItemName("Butterfingers");
        item.setItemPrice(1.30);
        item.setItemQuantity(1);
        item.setItemId(3);
        allItems.put(item.getItemId(), item);

        item = new Item();
        item.setItemName("Chips");
        item.setItemPrice(1.85);
        item.setItemQuantity(3);
        item.setItemId(4);
        allItems.put(item.getItemId(), item);

        item = new Item();
        item.setItemName("Slim Jim");
        item.setItemPrice(0.95);
        item.setItemQuantity(20);
        item.setItemId(5);
        allItems.put(item.getItemId(), item);
    }

    @Override
    public List<Item> getDisplayItems() {
        List<Item> displayItems = new ArrayList<>();
        for (Item item : allItems.values()) {
            if (item.getItemQuantity() > 0) {
                displayItems.add(item);
            }
        }
        return displayItems;
    }

    @Override
    public List<Item> getAllInventoryItems() {
        return allItems.values().stream().collect(Collectors.toList());
    }

    @Override
    public Item getItem(int itemId){
        return allItems.get(itemId);
    }
    
    @Override
    public Item buyItem(int chosenItemId) {
        Item item = allItems.get(chosenItemId);
        if (item.getItemQuantity() > 0) {
            item.setItemQuantity(item.getItemQuantity() - 1);
        }
        return item;
    }

    @Override
    public void createItem(Item item) {
        item.setItemId(itemIdCounter);
        itemIdCounter++;
        allItems.put(item.getItemId(), item);
    }

    @Override
    public void updateItem(Item item) {
        allItems.put(item.getItemId(), item);
    }

    @Override
    public void deleteItem(int itemId) {
        allItems.remove(itemId);
    }

}
