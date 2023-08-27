package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Address;
import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.model.dto.MedicalStaffDto;
import com.bloodbank.BloodBank.repository.AddressRepository;
import com.bloodbank.BloodBank.repository.BloodCenterRepository;
import com.bloodbank.BloodBank.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public MedicalStaff findById(Integer id){
        return medialStaffRepository.findById(id).orElseGet(null);
    }

    public List<MedicalStaff> findAll(){
        return medialStaffRepository.findAll();
    }

    private boolean isSameAddress(Address first, Address second){
       return first.getCity().equals(second.getCity()) && first.getCountry().equals(second.getCountry()) &&
                first.getNumber().equals(second.getNumber())&& first.getStreet().equals(second.getStreet());
    }

    public MedicalStaff updateMedicalStaff(MedicalStaff ms){
        Address newAddress = ms.getAddress();
        boolean found = false;
        for(Address a: addressRepository.findAll()){
            if(isSameAddress(a, newAddress)){
                found = true;
                newAddress = a;
                break;
            }
        }

        if(found){
            ms.setAddress(newAddress);
        } else {
            newAddress.setId(-1);
            Address address = addressRepository.save(newAddress);
            ms.setAddress(address);
        }

        return  medialStaffRepository.save(ms);
    }

    public List<MedicalStaffDto> findAllByBloodCenterId(Integer id){
        List<MedicalStaff> medicalStaffs = medialStaffRepository.findAllByBloodCenterId(id);
        List<MedicalStaffDto> dtoList = new ArrayList<>();

        for(MedicalStaff m: medicalStaffs){
            dtoList.add(new MedicalStaffDto(
                    m.getId(),
                    m.getName(),
                    m.getSurname(),
                    m.getJmbg(),
                    m.getGender(),
                    m.getEmail(),
                    m.getAddress().getId(),
                    m.getBloodCenter().getId()
            ));
        }

        return dtoList;
    }
}
