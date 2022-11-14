package com.bloodbank.BloodBank.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class BloodCenter implements Serializable {
    @Id
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    private String description;
    private float averageScore;
}
