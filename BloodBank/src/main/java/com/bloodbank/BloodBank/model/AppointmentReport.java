package com.bloodbank.BloodBank.model;

import java.io.Serializable;

public class AppointmentReport implements Serializable {
    private int id;
    private ScheduledAppointment appointment;
    private String content;
    private boolean canGiveBlood;
}
