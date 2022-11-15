package com.bloodbank.BloodBank.model.dto;

import com.bloodbank.BloodBank.model.Address;

public class AddressDto {

    private int id;
    private String street;
    private String number;
    private String city;
    private String country;

    public AddressDto(Address address){
        this.id = address.getId();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.country = address.getCountry();
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
