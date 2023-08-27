package com.bloodbank.BloodBank.model.dto;

import com.bloodbank.BloodBank.model.Address;

public class BloodCenterViewDto {

    private int id;
    private String name;

    private String street;
    private String streetNum;

    private String country;

    private String city;
    private String description;
    private float avarageScore;

    public BloodCenterViewDto() {
    }

    public BloodCenterViewDto(int id, String name, String street, String streetNum, String country, String city, String description, float avarageScore) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.streetNum = streetNum;
        this.country = country;
        this.city = city;
        this.description = description;
        this.avarageScore = avarageScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAvarageScore() {
        return avarageScore;
    }

    public void setAvarageScore(float avarageScore) {
        this.avarageScore = avarageScore;
    }
}
