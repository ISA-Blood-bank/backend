package com.bloodbank.BloodBank.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
public class BloodCenter implements Serializable {
    @Id
    private int id;
    private String name;
    private Address address;
    private String description;
    private float averageScore;
}
