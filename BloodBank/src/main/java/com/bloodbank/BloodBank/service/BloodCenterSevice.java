package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.repository.BloodCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodCenterSevice {

    @Autowired
    private BloodCenterRepository bloodCenterRepository;

    public BloodCenterSevice(BloodCenterRepository bloodCenterRepository){
        this.bloodCenterRepository = bloodCenterRepository;
    }
    public List<BloodCenter> findAll(){
        return bloodCenterRepository.findAll();
    }

}
