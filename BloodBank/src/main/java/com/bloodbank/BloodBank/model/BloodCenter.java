package com.bloodbank.BloodBank.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class BloodCenter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    private String description;
    private float averageScore;

    public BloodCenter() {
    }
    public BloodCenter(int id, String name, Address address, String description, float averageScore) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageScore = averageScore;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public String toString() {
        return "BloodCenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", description='" + description + '\'' +
                ", averageScore=" + averageScore +
                '}';
    }
}
