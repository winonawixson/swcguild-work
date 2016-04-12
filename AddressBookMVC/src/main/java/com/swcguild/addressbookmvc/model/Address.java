/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Winona Wixson
 */
public class Address {

    private int addressId;

    @NotEmpty(message = "You must supply a value for First Name.")
    @Length(max = 50, message = "First name must be no more than 50 characters in length")
    private String firstName;
    
    @NotEmpty(message = "You must supply a value for Last Name.")
    @Length(max = 50, message = "Last name must be no more than 50 characters in length")
    private String lastName;
    
    @NotEmpty(message = "You must supply a value for Street Address.")
    @Length(max = 100, message = "Street adddress must be no more than 100 characters in length")
    private String streetAddress;
    
    @NotEmpty(message = "You must supply a value for City.")
    @Length(max = 50, message = "City must be no more than 50 characters in length")
    private String city;
    
    @NotEmpty(message = "You must supply a value for State.")
    @Length(max = 50, message = "State must be no more than 50 characters in length")
    private String state;
    
    @NotEmpty(message = "You must supply a value for Zip code.")
    @Length(max = 10, message = "First name must be no more than 10 characters in length")
    private String zip;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.addressId;
        hash = 71 * hash + Objects.hashCode(this.firstName);
        hash = 71 * hash + Objects.hashCode(this.lastName);
        hash = 71 * hash + Objects.hashCode(this.streetAddress);
        hash = 71 * hash + Objects.hashCode(this.city);
        hash = 71 * hash + Objects.hashCode(this.state);
        hash = 71 * hash + Objects.hashCode(this.zip);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (this.addressId != other.addressId) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.streetAddress, other.streetAddress)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        return true;
    }

}
