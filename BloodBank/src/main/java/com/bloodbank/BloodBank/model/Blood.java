package com.bloodbank.BloodBank.model;

import com.bloodbank.BloodBank.model.enums.BloodType;

import java.io.Serializable;

public class Blood implements Serializable {
    private int id;
    private BloodType type;
    private float quantity;
    private BloodCenter bloodCenter;
}
