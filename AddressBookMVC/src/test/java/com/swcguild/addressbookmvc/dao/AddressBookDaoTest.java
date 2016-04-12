/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.model.Address;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Winona Wixson
 */
public class AddressBookDaoTest {

    private AddressBookDao dao;

    public AddressBookDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("testDao", AddressBookDao.class);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void addGetDeleteAddress() {
        Address newAddress = new Address();
        newAddress.setFirstName("Jane");
        newAddress.setLastName("Doe");
        newAddress.setStreetAddress("12 Blueberry Ln");
        newAddress.setCity("Louisville");
        newAddress.setState("KY");
        newAddress.setZip("40444");

        dao.addAddress(newAddress);

        Address foundAddress = dao.getAddressById(newAddress.getAddressId());

        Assert.assertEquals(newAddress, foundAddress);

        dao.removeAddress(newAddress.getAddressId());

        Assert.assertNull(dao.getAddressById(newAddress.getAddressId()));

    }

    @Test
    public void addUpdateContact() {
        Address newAddress = new Address();
        newAddress.setFirstName("Jane");
        newAddress.setLastName("Doe");
        newAddress.setStreetAddress("12 Blueberry Ln");
        newAddress.setCity("Louisville");
        newAddress.setState("KY");
        newAddress.setZip("40444");

        dao.addAddress(newAddress);

        newAddress.setFirstName("JimmyJoe");
        dao.updateAddress(newAddress);

        Address foundAddress = dao.getAddressById(newAddress.getAddressId());

        Assert.assertEquals(newAddress, foundAddress);
    }

    @Test
    public void getAllContacts() {
        Address newAddress = new Address();
        newAddress.setFirstName("Jane");
        newAddress.setLastName("Doe");
        newAddress.setStreetAddress("12 Blueberry Ln");
        newAddress.setCity("Louisville");
        newAddress.setState("KY");
        newAddress.setZip("40444");

        dao.addAddress(newAddress);

        Address newAddress2 = new Address();
        newAddress2.setFirstName("JimmyJoe");
        newAddress2.setLastName("Jones");
        newAddress2.setStreetAddress("15 Green Ln");
        newAddress2.setCity("Belmont");
        newAddress2.setState("NH");
        newAddress2.setZip("03413");

        dao.addAddress(newAddress2);

        List<Address> aList = dao.getAllAddresses();
        Assert.assertEquals(aList.size(), 6);
        //2 addresses that were just added and 4 that are added in the 
        //dao constructor
    }

    @Test
    public void searchContacts() {
        Address newAddress = new Address();
        newAddress.setFirstName("Jane");
        newAddress.setLastName("Smith");
        newAddress.setStreetAddress("12 Blueberry Ln");
        newAddress.setCity("Louisville");
        newAddress.setState("KY");
        newAddress.setZip("40444");

        dao.addAddress(newAddress);

        Address newAddress2 = new Address();
        newAddress2.setFirstName("JimmyJoe");
        newAddress2.setLastName("Jones");
        newAddress2.setStreetAddress("15 Green Ln");
        newAddress2.setCity("Somerville");
        newAddress2.setState("NH");
        newAddress2.setZip("03413");

        dao.addAddress(newAddress2);

        Address newAddress3 = new Address();
        newAddress3.setFirstName("Susan");
        newAddress3.setLastName("Smith");
        newAddress3.setStreetAddress("15 Shiny Ln");
        newAddress3.setCity("Silverville");
        newAddress3.setState("SC");
        newAddress3.setZip("93842");

        dao.addAddress(newAddress3);

        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.LAST_NAME, "Smith");
        List<Address> aList = dao.searchAddresses(criteria);
        assertEquals(2, aList.size());
        //assertEquals(newAddress, aList.get(0));
        
        criteria.put(SearchTerm.LAST_NAME, "Doe");
        aList = dao.searchAddresses(criteria);
        assertEquals(0, aList.size());
        
        criteria = new HashMap<>();
        criteria.put(SearchTerm.STREET_ADDRESS, "15 Shiny Ln");
        aList = dao.searchAddresses(criteria);
        assertEquals(1, aList.size());
        assertEquals(newAddress3, aList.get(0));
        
        criteria = new HashMap<>();
        criteria.put(SearchTerm.CITY, "Louisville");
        aList = dao.searchAddresses(criteria);
        assertEquals(1, aList.size());
        assertEquals(newAddress, aList.get(0));
        
        criteria.put(SearchTerm.CITY, "Silverville");
        aList = dao.searchAddresses(criteria);
        assertEquals(1, aList.size());
        assertEquals(newAddress3, aList.get(0));
        
        criteria.put(SearchTerm.CITY, "Somerville");
        aList = dao.searchAddresses(criteria);
        assertEquals(1, aList.size());
        assertEquals(newAddress2, aList.get(0));
    }
}
