package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Address;
import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.repository.AddressRepository;
import com.bloodbank.BloodBank.repository.BloodCenterRepository;
import com.bloodbank.BloodBank.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Set;

@Service
public class MedicalStaffService {
    @Autowired
    private MedicalStaffRepository medialStaffRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private BloodCenterRepository bloodCenterRepository;
    Address identical= new Address();
    MedicalStaff identicalMs= new MedicalStaff();
    private boolean exists(MedicalStaff ms){
        boolean found=false;
        for(Address a :  addressRepository.findAll())
        {
              if(a.getCity().equals(ms.getAddress().getCity()) && a.getCountry().equals(ms.getAddress().getCountry()) &&
                    a.getNumber().equals(ms.getAddress().getNumber())&& a.getStreet().equals(ms.getAddress().getStreet()))
            {
                identical=a;
                found=true;
                return found;

            }
        }
        return found;
    }
    private boolean existsMedicalStaff(MedicalStaff ms){
        boolean found=false;
        for(MedicalStaff a :  medialStaffRepository.findAll())
        {
            if((a.getName().equals(ms.getName())&&a.getSurname().equals(ms.getSurname())&&
                    a.getEmail().equals(ms.getEmail())) || a.getJmbg().equals(ms.getJmbg()))
            {
                identicalMs=a;
                found=true;
                return found;

            }
        }
        return found;
    }
    public MedicalStaff addMedicalStaff(MedicalStaff ms){
        if(exists(ms)==true)
        {
            ms.setAddress(identical);
        }
        else {
            Address address =addressRepository.save(ms.getAddress());
            ms.setAddress(address);
        }

        ms.setPassword("1111");
        if(existsMedicalStaff(ms)==false)
        {
            return medialStaffRepository.save(ms);
        }
        return null;
    }
}
