package com.bloodbank.BloodBank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class BloodCenterGrade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private RegistredUser user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "blood_center_id", referencedColumnName = "id")
    private BloodCenter bloodCenter;
    private float grade;

    public BloodCenterGrade(int id, RegistredUser user, BloodCenter bloodCenter, float grade) {
        this.id = id;
        this.user = user;
        this.bloodCenter = bloodCenter;
        this.grade = grade;
    }

    public BloodCenterGrade() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RegistredUser getUser() {
        return user;
    }

    public void setUser(RegistredUser user) {
        this.user = user;
    }

    public BloodCenter getBloodCenter() {
        return bloodCenter;
    }

    public void setBloodCenter(BloodCenter bloodCenter) {
        this.bloodCenter = bloodCenter;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
