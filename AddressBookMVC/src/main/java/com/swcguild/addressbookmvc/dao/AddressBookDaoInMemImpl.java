/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.model.Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Winona Wixson
 */
public class AddressBookDaoInMemImpl implements AddressBookDao {

    private Map<Integer, Address> addressMap = new HashMap<>();
    private static int addressIdCounter = 0;

    public AddressBookDaoInMemImpl() {
    
            Address address = new Address();
            address.setFirstName("Susie");
            address.setLastName("Rusie");
            address.setStreetAddress("12 Blue Ln");
            address.setCity("Villeville");
            address.setState("JF");
            address.setZip("93758");
            this.addAddress(address);
            
            address = new Address();
            address.setFirstName("George");
            address.setLastName("Humington");
            address.setStreetAddress("12 Green Ln");
            address.setCity("Belmont");
            address.setState("GW");
            address.setZip("47238");
            this.addAddress(address);
            
            address = new Address();
            address.setFirstName("Hubert");
            address.setLastName("Rubert");
            address.setStreetAddress("12 Yellow Ln");
            address.setCity("Yorktown");
            address.setState("IF");
            address.setZip("23457");
            this.addAddress(address);
            
            address = new Address();
            address.setFirstName("Wilbur");
            address.setLastName("Jorque");
            address.setStreetAddress("12 Purple Ln");
            address.setCity("Rookie");
            address.setState("PG");
            address.setZip("11111");
            this.addAddress(address);
    }
    
    
    
    @Override
    public Address addAddress(Address address) {
        address.setAddressId(addressIdCounter);
        addressIdCounter++;
        
        addressMap.put(address.getAddressId(), address);
        return address;
    }

    @Override
    public void removeAddress(int addressId) {
        addressMap.remove(addressId);
    }

    @Override
    public void updateAddress(Address address) {
        addressMap.put(address.getAddressId(), address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return new ArrayList(addressMap.values());
    }

    @Override
    public Address getAddressById(int addressId) {
        return addressMap.get(addressId);
    }

    @Override
    public List<Address> searchAddresses(Map<SearchTerm, String> criteria) {
        //get all criteria out
        String firstNameCriteria = criteria.get(SearchTerm.FIRST_NAME);
        String lastNameCriteria = criteria.get(SearchTerm.LAST_NAME);
        String stAddressCriteria = criteria.get(SearchTerm.STREET_ADDRESS);
        String cityCriteria = criteria.get(SearchTerm.CITY);
        String stateCriteria = criteria.get(SearchTerm.STATE);
        String zipCriteria = criteria.get(SearchTerm.ZIP);

        //declare the predicates
        Predicate<Address> firstNamePred;
        Predicate<Address> lastNamePred;
        Predicate<Address> stAddressPred;
        Predicate<Address> cityPred;
        Predicate<Address> statePred;
        Predicate<Address> zipPred;
        
        //declare and initialize an "all pass" predicate filter
        Predicate<Address> allPass = (contact) -> {return true;};
        
        firstNamePred = (firstNameCriteria == null || firstNameCriteria.isEmpty()) 
                ? allPass : (contact) -> contact.getFirstName().contains(firstNameCriteria);
        lastNamePred = (lastNameCriteria == null || lastNameCriteria.isEmpty()) 
                ? allPass : (contact) -> contact.getLastName().contains(lastNameCriteria);
        stAddressPred = (stAddressCriteria == null || stAddressCriteria.isEmpty()) 
                ? allPass : (contact) -> contact.getStreetAddress().contains(stAddressCriteria);
        cityPred = (cityCriteria == null || cityCriteria.isEmpty()) 
                ? allPass : (contact) -> contact.getCity().contains(cityCriteria);
        statePred = (stateCriteria == null || stateCriteria.isEmpty()) 
                ? allPass : (contact) -> contact.getState().contains(stateCriteria);
        zipPred = (zipCriteria == null || zipCriteria.isEmpty()) 
                ? allPass : (contact) -> contact.getZip().contains(zipCriteria);
        
        return addressMap.values().stream()
                .filter(firstNamePred)
                .filter(lastNamePred)
                .filter(stAddressPred)
                .filter(cityPred)
                .filter(statePred)
                .filter(zipPred)
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> searchAddresses(Predicate<Address> filter) {
        return addressMap.values().stream()
                .filter(filter)
                .collect(Collectors.toList());
                
    }
    
}
