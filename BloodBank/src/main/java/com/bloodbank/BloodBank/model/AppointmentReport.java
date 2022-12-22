package com.bloodbank.BloodBank.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class AppointmentReport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scheduled_appointment_id")
    private ScheduledAppointment appointment;
    private String content;
    private boolean canGiveBlood;

    public AppointmentReport(int id, ScheduledAppointment appointment, String content, boolean canGiveBlood) {
        this.id = id;
        this.appointment = appointment;
        this.content = content;
        this.canGiveBlood = canGiveBlood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ScheduledAppointment getAppointment() {
        return appointment;
    }

    public void setAppointment(ScheduledAppointment appointment) {
        this.appointment = appointment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCanGiveBlood() {
        return canGiveBlood;
    }

    public void setCanGiveBlood(boolean canGiveBlood) {
        this.canGiveBlood = canGiveBlood;
    }

    public AppointmentReport() {
    }
}
