package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.Blood;
import com.bloodbank.BloodBank.model.ScheduledAppointment;
import com.bloodbank.BloodBank.model.dto.AdditionalInfoDto;
import com.bloodbank.BloodBank.repository.BloodRepository;
import com.bloodbank.BloodBank.repository.ScheduledAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BloodService {
    @Autowired
    private BloodRepository bloodRepository;

    @Autowired
    private ScheduledAppointmentRepository scheduledAppointmentRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Blood addBloodToCentre(AdditionalInfoDto additionalInfoDto){
        Integer schId = additionalInfoDto.getScheduledAppointmentId();
        ScheduledAppointment sch = scheduledAppointmentRepository.findById(schId).orElseGet(null);
        Appointment appointment = sch.getAppointment();
        Blood blood = bloodRepository.getBloodByBloodCenterIdAndBloodType(
                appointment.getBloodCenter().getId(),
                additionalInfoDto.getBloodType()
        );

        blood.setQuantity(blood.getQuantity() + 1); //racuna se jedinica krvi

        bloodRepository.save(blood);

        return blood;
    }
}
