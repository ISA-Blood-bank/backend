package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/registeredusers")
public class RegisteredUserController {
    @Autowired
    private RegisteredUserService regUserService;
    @GetMapping("/all")
    public ResponseEntity<List<RegistredUser>> getAllUsers(){
        List<RegistredUser> users = regUserService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RegistredUser> getUserById(@PathVariable("id") Integer id){
        RegistredUser user = regUserService.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<RegistredUser> addRegisteredUser(@RequestBody RegistredUser user) {
        RegistredUser newUser = regUserService.addRegisteredUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<RegistredUser> updateRegisteredUser(@RequestBody RegistredUser user) {
        RegistredUser newUser = regUserService.updateRegisteredUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
