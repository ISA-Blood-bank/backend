package com.bloodbank.BloodBank.model.dto;

public class RejectionReasonDto {
    private int id;
    private int scheduledAppointmentId;
    private int medicalStaffId;

    private String rejectionReason;

    public RejectionReasonDto() {
    }

    public RejectionReasonDto(int id, int scheduledAppointmentId, int medicalStaffId, String rejectionReason) {
        this.id = id;
        this.scheduledAppointmentId = scheduledAppointmentId;
        this.medicalStaffId = medicalStaffId;
        this.rejectionReason = rejectionReason;
    }

    public RejectionReasonDto(int scheduledAppointmentId, int medicalStaffId, String rejectionReason) {
        this.scheduledAppointmentId = scheduledAppointmentId;
        this.medicalStaffId = medicalStaffId;
        this.rejectionReason = rejectionReason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScheduledAppointmentId() {
        return scheduledAppointmentId;
    }

    public void setScheduledAppointmentId(int scheduledAppointmentId) {
        this.scheduledAppointmentId = scheduledAppointmentId;
    }

    public int getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(int medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}
