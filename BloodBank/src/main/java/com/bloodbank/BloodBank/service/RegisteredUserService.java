package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.RegistredUser;
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
    public RegistredUser save(RegistredUser registredUser){
        return regUserRep.save(registredUser);
    }
    public void remove(Integer id){
        regUserRep.deleteById(id);
    }


}
