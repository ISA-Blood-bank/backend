package com.bloodbank.BloodBank.model;

import com.bloodbank.BloodBank.model.enums.Gender;

import javax.persistence.*;
@Entity
public class MedicalStaff{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "registered_user_id")
    private RegistredUser registeredUser;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "blood_center_id")
    private BloodCenter bloodCenter;

    public MedicalStaff() {
    }

    public MedicalStaff(int id, RegistredUser registeredUser, BloodCenter bloodCenter) {
        this.id = id;
        this.registeredUser = registeredUser;
        this.bloodCenter = bloodCenter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RegistredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegistredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    public BloodCenter getBloodCenter() {
        return bloodCenter;
    }

    public void setBloodCenter(BloodCenter bloodCenter) {
        this.bloodCenter = bloodCenter;
    }
}
