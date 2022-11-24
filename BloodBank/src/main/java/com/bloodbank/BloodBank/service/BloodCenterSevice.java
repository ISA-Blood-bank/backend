package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Address;
import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.repository.AddressRepository;
import com.bloodbank.BloodBank.repository.BloodCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodCenterSevice {

    @Autowired
    private BloodCenterRepository bloodCenterRepository;
    @Autowired
    private AddressRepository addressRepository;
    public BloodCenterSevice(BloodCenterRepository bloodCenterRepository){
        this.bloodCenterRepository = bloodCenterRepository;
    }
    public List<BloodCenter> findAll(){
        return bloodCenterRepository.findAll();
    }
    Address identical= new Address();
    BloodCenter identicalBC= new BloodCenter();
    private boolean exists(BloodCenter ms){
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
    private boolean existsBloodCenter(BloodCenter ms){
        boolean found=false;
        for(BloodCenter a :  bloodCenterRepository.findAll())
        {
            if(a.getName().equals(ms.getName())&&a.getAddress().equals(ms.getAddress())&&a.getDescription().equals(ms.getDescription()))
            {
                identicalBC=a;
                found=true;
                return found;
            }
        }
        return found;
    }
    public BloodCenter addBloodCenter(BloodCenter bc){
        if(exists(bc)==true)
        {
            bc.setAddress(identical);
        }
        else {
            Address address =addressRepository.save(bc.getAddress());
            bc.setAddress(address);
        }
        if(existsBloodCenter(bc)==false)
        {
            return bloodCenterRepository.save(bc);
        }
        return null;
    }

    private boolean isSameAddress(Address first, Address second){
        return first.getCity().equals(second.getCity()) && first.getCountry().equals(second.getCountry()) &&
                first.getNumber().equals(second.getNumber())&& first.getStreet().equals(second.getStreet());
    }

    public BloodCenter update(BloodCenter bc){
        Address newAddress = bc.getAddress();
        boolean found = false;
        for(Address a: addressRepository.findAll()){
            if(isSameAddress(newAddress, a)){
                found = true;
                newAddress = a;
                break;
            }
        }

        if (found){
            bc.setAddress(newAddress);
        } else {
            newAddress.setId(-1);
            Address address = addressRepository.save(newAddress);
            bc.setAddress(address);
        }
            return bloodCenterRepository.save(bc);
    }

    public BloodCenter findOne(Integer id){
        return  bloodCenterRepository.findById(id).orElseGet(null);
    }
}
