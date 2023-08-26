package com.bloodbank.BloodBank.model.dto;

import com.bloodbank.BloodBank.model.enums.BloodType;

public class BloodDto {
    private int id;
    private BloodType bloodType;

    private float quantity;
    private int bloodCenterId;

    public BloodDto() {
    }

    public BloodDto(int id, BloodType bloodType, float quantity, int bloodCenterId) {
        this.id = id;
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.bloodCenterId = bloodCenterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public int getBloodCenterId() {
        return bloodCenterId;
    }

    public void setBloodCenterId(int bloodCenterId) {
        this.bloodCenterId = bloodCenterId;
    }
}
