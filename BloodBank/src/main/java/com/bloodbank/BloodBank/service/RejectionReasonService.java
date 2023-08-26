package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.model.RejectionInfo;
import com.bloodbank.BloodBank.model.ScheduledAppointment;
import com.bloodbank.BloodBank.model.dto.RejectionReasonDto;
import com.bloodbank.BloodBank.repository.RejectionReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RejectionReasonService {
    @Autowired
    private RejectionReasonRepository rejectionReasonRepository;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @Autowired
    private ScheduledAppointmentService scheduledAppointmentService;

    public RejectionInfo findById(Integer id){
        return rejectionReasonRepository.findById(id).orElseGet(null);
    }

    public RejectionInfo save(RejectionReasonDto dto){
        MedicalStaff ms = medicalStaffService.findById(dto.getMedicalStaffId());
        ScheduledAppointment sc = scheduledAppointmentService.findById(dto.getScheduledAppointmentId());

        RejectionInfo r = new RejectionInfo(sc, ms, dto.getRejectionReason());
        r = rejectionReasonRepository.save(r);

        return r;
    }
}
