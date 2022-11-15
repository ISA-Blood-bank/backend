package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository regUserRep;

    public RegistredUser findOne(Integer id){
        return regUserRep.findById(id).orElseGet(null);
    }
    public List<RegistredUser> findAll(){
        return regUserRep.findAll();
    }
    public RegistredUser updateRegisteredUser(RegistredUser registredUser){
        return regUserRep.save(registredUser);
    }
    public RegistredUser addRegisteredUser(RegistredUser registredUser){
        registredUser.setCategory(Category.REGULAR);
        registredUser.setPoints((float)0.0);
        registredUser.setPenalties(0);
        return regUserRep.save(registredUser);
    }
    public void remove(Integer id){
        regUserRep.deleteById(id);
    }


}
