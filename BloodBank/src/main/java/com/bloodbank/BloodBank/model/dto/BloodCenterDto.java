package com.bloodbank.BloodBank.model.dto;

import com.bloodbank.BloodBank.model.Address;
import com.bloodbank.BloodBank.model.BloodCenter;

public class BloodCenterDto {

    private int id;
    private String name;
    private Address addres;
    private String description;
    private float avarageScore;

    public BloodCenterDto(BloodCenter bloodCenter) {
        this.id = bloodCenter.getId();
        this.name = bloodCenter.getName();
        this.addres = bloodCenter.getAddress();
        this.description = bloodCenter.getDescription();
        this.avarageScore = bloodCenter.getAverageScore();
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

    public Address getAddres() {
        return addres;
    }

    public void setAddres(Address addres) {
        this.addres = addres;
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

    public BloodCenterDto() {
    }
}
