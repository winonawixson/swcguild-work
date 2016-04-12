/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.controller;

import com.swcguild.addressbookmvc.dao.AddressBookDao;
import com.swcguild.addressbookmvc.dao.SearchTerm;
import com.swcguild.addressbookmvc.model.Address;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Winona Wixson
 */
@Controller
public class HomeControllerNoAjax {

    private AddressBookDao dao;

    @Inject
    public HomeControllerNoAjax(AddressBookDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayAddressBookNoAjax", method = RequestMethod.GET)
    public String displayAddressBookNoAjax(Model model) {

        List<Address> allAddresses = dao.getAllAddresses();
        model.addAttribute("addressList", allAddresses);

        return "noAjax/displayAddresses";
    }

    @RequestMapping(value = "newAddressNoAjax", method = RequestMethod.GET)
    public String displayNewAddressForm(){
        
        return "noAjax/addAddress";
    }
    
    @RequestMapping(value = "addAddressNoAjax", method = RequestMethod.POST)
    public String addAddressNoAjax(HttpServletRequest req){
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String stAddress = req.getParameter("streetAddress");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        
        Address address = new Address();
        address.setFirstName(firstName);
        address.setLastName(lastName);
        address.setStreetAddress(stAddress);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);
        
        dao.addAddress(address);
        
        return "redirect:displayAddressBookNoAjax";
    }
    
    @RequestMapping(value = "deleteAddressNoAjax", method = RequestMethod.GET)
    public String deleteAddressNoAjax(HttpServletRequest req) {
        String addressId = req.getParameter("aId");

        int addressIdInt = sanitizeAddressId(addressId);
        if (addressIdInt >= 0) {
            dao.removeAddress(addressIdInt);
        }
        
        return "redirect:displayAddressBookNoAjax";
    }
    
    @RequestMapping(value = "editAddressNoAjax", method = RequestMethod.GET)
    public String displayEditFormNoAjax(HttpServletRequest req, Model model){

        int addressIdInt = sanitizeAddressId(req.getParameter("aId"));
        if (addressIdInt >= 0) {
           Address address = dao.getAddressById(addressIdInt);
           model.addAttribute("addressToEdit", address);
        }
        
        return "noAjax/editAddressForm";
    }
    
    @RequestMapping(value="editAddressNoAjax", method = RequestMethod.POST)
    public String editModelAddressNoAjax(@Valid @ModelAttribute("addressToEdit")Address address, 
                                            BindingResult result){
        if(result.hasErrors()){
            return "editAddressFormWithoutAjax";
        }
        dao.updateAddress(address);
        
        return "redirect:displayAddressBookNoAjax";
    }

    private int sanitizeAddressId(String addressId) {
        int addressIdInt;
        try {
            addressIdInt = Integer.parseInt(addressId);

        } catch (NumberFormatException ex) {
            addressIdInt = -1;
        }
        return addressIdInt;
    }
    
    @RequestMapping(value="/displaySearchFormWithoutAjax", method = RequestMethod.GET)
    public String displaySearchWithoutAjax(Model model){
        return "noAjax/searchAddressesForm";
    }
    
    @RequestMapping(value="/searchWithoutAjax", method = RequestMethod.POST)
    public String searchWithoutAjax(HttpServletRequest req, Model model){
        
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String stAddress = req.getParameter("streetAddress");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        
        Map<SearchTerm, String> criteriaForSearch = new HashMap<>();
        criteriaForSearch.put(SearchTerm.FIRST_NAME, firstName);
        criteriaForSearch.put(SearchTerm.LAST_NAME, lastName);
        criteriaForSearch.put(SearchTerm.STREET_ADDRESS, stAddress);
        criteriaForSearch.put(SearchTerm.CITY, city);
        criteriaForSearch.put(SearchTerm.STATE, state);
        criteriaForSearch.put(SearchTerm.ZIP, zip);
        
        List<Address> foundAddresses = dao.searchAddresses(criteriaForSearch);
        model.addAttribute("addressList", foundAddresses);
        model.addAttribute("fromSearch", true);
        model.addAttribute("listSize", foundAddresses.size());
        
        return "noAjax/displayAddresses";
    }

}
