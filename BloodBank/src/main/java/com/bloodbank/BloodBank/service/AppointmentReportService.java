package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.AppointmentReport;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.repository.AppointmentReportRepository;
import com.bloodbank.BloodBank.security.auth.TokenBasedAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentReportService {

    @Autowired
    private AppointmentReportRepository appointmentReportRepository;

    public List<AppointmentReport> findAllSorted(int page, int size, String sortList, String order){

        TokenBasedAuthentication authentication = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
        RegistredUser user = (RegistredUser) authentication.getPrincipal();
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortList));
        if(order.equals("ASC")){
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortList));
        }else {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortList));
        }
        return appointmentReportRepository.findAllSorted(user.getId(), pageable);
    }
}
