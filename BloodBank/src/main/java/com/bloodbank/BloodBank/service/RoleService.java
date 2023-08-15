package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Role;
import com.bloodbank.BloodBank.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findById(Long id){
        Role auth = this.roleRepository.findById(id).orElseGet(null);
        return auth;
    }

    public List<Role> findByName(String name){
        List<Role> roles = this.roleRepository.findByName(name);
        return roles;
    }
}
