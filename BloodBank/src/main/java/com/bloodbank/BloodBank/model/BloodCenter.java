package com.bloodbank.BloodBank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class BloodCenter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    private String description;
    private float averageScore;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MedicalStaff> medicalStaff = new HashSet<MedicalStaff>();

    @Override
    public String toString() {
        return "BloodCenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", description='" + description + '\'' +
                ", averageScore=" + averageScore +
                ", medicalStaff=" + medicalStaff +
                '}';
    }

    public BloodCenter(int id, String name, Address address, String description, float averageScore, Set<MedicalStaff> medicalStaff) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageScore = averageScore;
        this.medicalStaff = medicalStaff;
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

    public Set<MedicalStaff> getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(Set<MedicalStaff> medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    public BloodCenter() {
    }
}
