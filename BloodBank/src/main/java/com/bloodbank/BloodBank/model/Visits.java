package com.bloodbank.BloodBank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Visits implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "registred_user_id", referencedColumnName = "id")
    private RegistredUser user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "blood_center_id", referencedColumnName = "id")
    private BloodCenter bloodCenter;

    public Visits() {
    }

    public Visits(RegistredUser user, BloodCenter bloodCenter) {
        this.user = user;
        this.bloodCenter = bloodCenter;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
