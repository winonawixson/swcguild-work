/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.model.Address;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 *
 * @author Winona Wixson
 */
public interface AddressBookDao {
    
    //add the Address to data store
    public Address addAddress(Address address);
    
    //remove the Address from the data store
    public void removeAddress(int addressId);
    
    //update the give address in the data store
    public void updateAddress(Address address);
    
    //retrieve all addresses from the data store
    public List<Address> getAllAddresses();
    
    //retrieve the address with the given id from the data store
    public Address getAddressById(int addressId);
    
    //search for addresses by the given search criteria values
    public List<Address> searchAddresses(Map<SearchTerm, String> criteria);
    
    //search all addresses by the given predicate filter
    public List<Address> searchAddresses(Predicate<Address> filter);
}
