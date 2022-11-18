package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Address;
import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.repository.AddressRepository;
import com.bloodbank.BloodBank.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository regUserRep;

    @Autowired
    private AddressRepository addressRepository;

    public RegistredUser findOne(Integer id){
        return regUserRep.findById(id).orElseGet(null);
    }
    public List<RegistredUser> findAll(){
        return regUserRep.findAll();
    }
    public RegistredUser updateRegisteredUser(RegistredUser registredUser){
        Address newAddress = registredUser.getAddress();
        boolean found = false;
        for(Address a : addressRepository.findAll()){
            if(a.getCity().equals(newAddress.getCity()) && a.getCountry().equals(newAddress.getCountry()) &&
                    a.getNumber().equals(newAddress.getNumber())&& a.getStreet().equals(newAddress.getStreet())){
                found = true;
                newAddress = a;
                break;
            }
        }

        if(found){
            registredUser.setAddress(newAddress);
        }
        else{
            newAddress.setId(-1);
            Address address =addressRepository.save(newAddress);
            registredUser.setAddress(address);
        }
        return regUserRep.save(registredUser);
    }
    public RegistredUser addRegisteredUser(RegistredUser registredUser){
        Address addresWithId = addressRepository.save(registredUser.getAddress());
        registredUser.setAddress(addresWithId);
        registredUser.setCategory(Category.REGULAR);
        registredUser.setPoints((float)0.0);
        registredUser.setPenalties(0);
        return regUserRep.save(registredUser);
    }
    public void remove(Integer id){
        regUserRep.deleteById(id);
    }


}
