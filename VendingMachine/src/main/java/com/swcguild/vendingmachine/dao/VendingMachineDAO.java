/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachine.dao;

import com.swcguild.vendingmachine.model.Item;
import java.util.List;

/**
 *
 * @author Winona Wixson
 */
public interface VendingMachineDAO {

    Item buyItem(int chosenItemId);

    void createItem(Item item);

    void deleteItem(int itemId);

    List<Item> getAllInventoryItems();

    List<Item> getDisplayItems();

    Item getItem(int itemId);

    void updateItem(Item item);
    
}
