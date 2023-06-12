package com.bloodbank.BloodBank.model;

import com.bloodbank.BloodBank.model.enums.BloodType;

import javax.persistence.*;

@Entity
@Table(name = "BloodQuantity")
public class BloodQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BloodType bloodType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "blood_center_id")
    private BloodCenter bloodCenter;

    private float quantity;

    public BloodQuantity(int id, BloodType bloodType, BloodCenter bloodCenter, float quantity) {
        this.id = id;
        this.bloodType = bloodType;
        this.bloodCenter = bloodCenter;
        this.quantity = quantity;
    }

    public BloodQuantity() {
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

    public BloodCenter getBloodCenter() {
        return bloodCenter;
    }

    public void setBloodCenter(BloodCenter bloodCenter) {
        this.bloodCenter = bloodCenter;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }


}
