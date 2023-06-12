package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Address;
import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.repository.AddressRepository;
import com.bloodbank.BloodBank.repository.BloodCenterRepository;
import com.bloodbank.BloodBank.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
              if(a.getCity().equals(ms.getRegisteredUser().getAddress().getCity()) && a.getCountry().equals(ms.getRegisteredUser().getAddress().getCountry()) &&
                    a.getNumber().equals(ms.getRegisteredUser().getAddress().getNumber())&& a.getStreet().equals(ms.getRegisteredUser().getAddress().getStreet()))
            {
                identical=a;
                found=true;
                return found;

            }
        }
        return found;
    }

    public int getBloodCenterIdByUserId (int userid){
        MedicalStaff medicalStaff = this.medialStaffRepository.findByRegUserId(userid);
        return medicalStaff.getBloodCenter().getId();
    }
    private boolean existsMedicalStaff(MedicalStaff ms){
        boolean found=false;
        for(MedicalStaff a :  medialStaffRepository.findAll())
        {
            if((a.getRegisteredUser().getName().equals(ms.getRegisteredUser().getName())&&a.getRegisteredUser().getSurname().equals(ms.getRegisteredUser().getSurname())&&
                    a.getRegisteredUser().getEmail().equals(ms.getRegisteredUser().getEmail())) || a.getRegisteredUser().getJmbg().equals(ms.getRegisteredUser().getJmbg()))
            {
                identicalMs=a;
                found=true;
                return found;

            }
        }
        return found;
    }
    public MedicalStaff addMedicalStaff(MedicalStaff ms){
      /*  if(exists(ms)==true)
        {
            ms.getRegisteredUser().setAddress(identical);
        }
        else {
            Address address =addressRepository.save(ms.getRegisteredUser().getAddress());
            ms.getRegisteredUser().setAddress(address);
        }*/

       // ms.getRegisteredUser().setPassword("1111");
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
        Address newAddress = ms.getRegisteredUser().getAddress();
        boolean found = false;
        for(Address a: addressRepository.findAll()){
            if(isSameAddress(a, newAddress)){
                found = true;
                newAddress = a;
                break;
            }
        }

        if(found){
            ms.getRegisteredUser().setAddress(newAddress);
        } else {
            newAddress.setId(-1);
            Address address = addressRepository.save(newAddress);
            ms.getRegisteredUser().setAddress(address);
        }

        return  medialStaffRepository.save(ms);
    }

    public List<MedicalStaff> findAllByBloodCenterId(Integer id){
        return  medialStaffRepository.findAllByBloodCenterId(id);
    }
}
