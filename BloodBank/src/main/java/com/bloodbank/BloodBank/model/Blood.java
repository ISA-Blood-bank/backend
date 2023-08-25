package com.bloodbank.BloodBank.model;

import com.bloodbank.BloodBank.model.enums.BloodType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Blood implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BloodType type;
    private float quantity;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "blood_center_id", referencedColumnName = "id")
    private BloodCenter bloodCenter;

    public Blood() {
    }

    public Blood(int id, BloodType type, float quantity, BloodCenter bloodCenter) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
        this.bloodCenter = bloodCenter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BloodType getType() {
        return type;
    }

    public void setType(BloodType type) {
        this.type = type;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public BloodCenter getBloodCenter() {
        return bloodCenter;
    }

    public void setBloodCenter(BloodCenter bloodCenter) {
        this.bloodCenter = bloodCenter;
    }

    @Override
    public String toString() {
        return "Blood{" +
                "id=" + id +
                ", type=" + type +
                ", quantity=" + quantity +
                ", bloodCenter=" + bloodCenter +
                '}';
    }
}
