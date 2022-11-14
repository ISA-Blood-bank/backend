package com.bloodbank.BloodBank.model;

import java.io.Serializable;
import java.time.LocalTime;

public class WorkingHours implements Serializable {
    private int id;
    private LocalTime start;
    private LocalTime end;
}
