package com.bloodbank.BloodBank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class RejectionInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scheduled_appointment_id")
    private ScheduledAppointment scheduledAppointment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medical_staff_id")
    private MedicalStaff medicalStaff;

    private String rejectionReason;

    public RejectionInfo() {
    }

    public RejectionInfo(int id, ScheduledAppointment scheduledAppointment, MedicalStaff medicalStaff, String rejectionReason) {
        this.id = id;
        this.scheduledAppointment = scheduledAppointment;
        this.medicalStaff = medicalStaff;
        this.rejectionReason = rejectionReason;
    }

    public RejectionInfo(ScheduledAppointment scheduledAppointment, MedicalStaff medicalStaff, String rejectionReason) {
        this.scheduledAppointment = scheduledAppointment;
        this.medicalStaff = medicalStaff;
        this.rejectionReason = rejectionReason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ScheduledAppointment getScheduledAppointment() {
        return scheduledAppointment;
    }

    public void setScheduledAppointment(ScheduledAppointment scheduledAppointment) {
        this.scheduledAppointment = scheduledAppointment;
    }

    public MedicalStaff getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RejectionInfo)) return false;
        RejectionInfo that = (RejectionInfo) o;
        return getId() == that.getId() && Objects.equals(getScheduledAppointment(), that.getScheduledAppointment()) && Objects.equals(getMedicalStaff(), that.getMedicalStaff()) && Objects.equals(getRejectionReason(), that.getRejectionReason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getScheduledAppointment(), getMedicalStaff(), getRejectionReason());
    }
}
