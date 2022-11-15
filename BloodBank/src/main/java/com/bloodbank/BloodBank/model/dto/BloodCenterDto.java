package com.bloodbank.BloodBank.model.dto;

import com.bloodbank.BloodBank.model.Address;
import com.bloodbank.BloodBank.model.BloodCenter;

public class BloodCenterDto {

    private int id;
    private String name;
    private AddressDto addresDto;
    private String description;
    private float avarageScore;

    public BloodCenterDto(BloodCenter bloodCenter) {
        this.id = bloodCenter.getId();
        this.name = bloodCenter.getName();
        this.addresDto = new AddressDto(bloodCenter.getAddress());
        this.description = bloodCenter.getDescription();
        this.avarageScore = bloodCenter.getAverageScore();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AddressDto getAddresDto() {
        return addresDto;
    }

    public String getDescription() {
        return description;
    }

    public float getAvarageScore() {
        return avarageScore;
    }
}
