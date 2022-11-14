package com.bloodbank.BloodBank.model;

import java.io.Serializable;

public class Complaint implements Serializable {
    private int id;
    private RegistredUser user;
    private String content;
}
