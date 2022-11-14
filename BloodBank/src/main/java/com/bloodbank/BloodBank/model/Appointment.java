package com.bloodbank.BloodBank.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {
    private int id;
    private LocalDateTime start;
    private float duration;
    private boolean available;
    private BloodCenter bloodCenter;
    private MedicalStaff medicalStaff;

}
