/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachine.dao;

import com.swcguild.vendingmachine.model.Item;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Winona Wixson
 */
public class VendingMachineDAOTest {

    private VendingMachineDAO dao;

    public VendingMachineDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //there are five items created in the dao constructor

        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("daoBean", VendingMachineDAO.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getDisplayItems() {
        List<Item> displayItems = dao.getDisplayItems();
        Assert.assertEquals(5, displayItems.size());
        
        for (int i = 0; i < displayItems.size(); i++) {
            Assert.assertTrue(displayItems.get(i).getItemQuantity() > 0);
        }
        
    }

    @Test
    public void createGetDisplayBuyGetDisplay() {
        Item item = new Item();
        item.setItemName("Soda");
        item.setItemPrice(1.25);
        item.setItemQuantity(1);
        dao.createItem(item);

        Assert.assertEquals(6, dao.getDisplayItems().size());

        dao.buyItem(item.getItemId()); //changes soda quant to 0
        //make sure soda is not in the display items any more
        Assert.assertEquals(5, dao.getDisplayItems().size());
    }

    @Test
    public void getAllInventoryItems() {
        Assert.assertEquals(5, dao.getAllInventoryItems().size());

        Item item = new Item();
        item.setItemName("Soda");
        item.setItemPrice(1.25);
        item.setItemQuantity(0);
        dao.createItem(item);

        //make sure even though the quantity is 0, it's still able to 
        //retrieved within all inventory items
        Assert.assertEquals(6, dao.getAllInventoryItems().size());

    }

    @Test
    public void createGetItem() {
        Item item = new Item();
        item.setItemName("Soda");
        item.setItemPrice(1.25);
        item.setItemQuantity(0);
        dao.createItem(item);

        Assert.assertEquals(item, dao.getItem(6));
    }

    @Test
    public void create2ItemsGetItems() {
        Item item = new Item();
        item.setItemName("Soda");
        item.setItemPrice(1.25);
        item.setItemQuantity(0);
        dao.createItem(item);

        Item item2 = new Item();
        item2.setItemName("Pizza");
        item2.setItemPrice(1.25);
        item2.setItemQuantity(0);
        dao.createItem(item2);

        Assert.assertEquals(item, dao.getItem(6));
        Assert.assertEquals(item2, dao.getItem(7));
    }

    @Test
    public void createDeleteGetItem() {
        Item item = new Item();
        item.setItemName("Soda");
        item.setItemPrice(1.25);
        item.setItemQuantity(3);
        dao.createItem(item);

        dao.deleteItem(6);
        Assert.assertNull(dao.getItem(6));
    }

    @Test
    public void createBuyCheckQuantity() {
        Item item = new Item();
        item.setItemName("Soda");
        item.setItemPrice(1.25);
        item.setItemQuantity(3);
        dao.createItem(item);
        dao.buyItem(6);
        Assert.assertEquals(2, dao.getItem(6).getItemQuantity());
    }

    @Test
    public void createBuyNoQuantCheckForSameQuantity() {
        Item item = new Item();
        item.setItemName("Soda");
        item.setItemPrice(1.25);
        item.setItemQuantity(0);
        dao.createItem(item);
        dao.buyItem(6);
        Assert.assertEquals(0, dao.getItem(6).getItemQuantity());
    }

    @Test
    public void createItemsCheckIds() {
        Item item = new Item();
        item.setItemName("Soda");
        item.setItemPrice(1.25);
        item.setItemQuantity(3);
        dao.createItem(item);

        Item item2 = new Item();
        item2.setItemName("Pizza");
        item2.setItemPrice(1.25);
        item2.setItemQuantity(3);
        dao.createItem(item2);

        Item item3 = new Item();
        item3.setItemName("Tacos");
        item3.setItemPrice(1.25);
        item3.setItemQuantity(5);
        dao.createItem(item3);

        Assert.assertEquals(item, dao.getItem(6));
        Assert.assertEquals(item2, dao.getItem(7));
        Assert.assertEquals(item3, dao.getItem(8));

    }

    @Test
    public void createChangeUpdateGetItem() {
        Item item = new Item();
        item.setItemName("Soda");
        item.setItemPrice(1.25);
        item.setItemQuantity(3);
        dao.createItem(item);
        
        item.setItemPrice(1.50);
        dao.updateItem(item);
        
        Assert.assertEquals(item, dao.getItem(6));
    }
    
    @Test
    public void createDeleteGetNull(){
        Item item = new Item();
        item.setItemName("Soda");
        item.setItemPrice(1.25);
        item.setItemQuantity(3);
        dao.createItem(item);
        dao.deleteItem(6);
        Assert.assertNull(dao.getItem(6));
    }
    
    @Test
    public void create2ItemsDeleteGetAllInventory(){
        Item item = new Item();
        item.setItemName("Soda");
        item.setItemPrice(1.25);
        item.setItemQuantity(3);
        dao.createItem(item);

        Item item2 = new Item();
        item2.setItemName("Pizza");
        item2.setItemPrice(1.25);
        item2.setItemQuantity(3);
        dao.createItem(item2);

        Assert.assertEquals(7, dao.getAllInventoryItems().size());
        
        dao.deleteItem(6);
        dao.deleteItem(7);
        
        Assert.assertEquals(5, dao.getAllInventoryItems().size());
    }
    
    
}
