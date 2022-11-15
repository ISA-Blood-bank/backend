package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/registeredusers")
public class RegisteredUserController {
    @Autowired
    private RegisteredUserService regUserService;
    
}
