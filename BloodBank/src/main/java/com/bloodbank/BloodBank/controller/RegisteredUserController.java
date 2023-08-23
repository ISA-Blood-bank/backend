package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.dto.RegistredUserDto;
import com.bloodbank.BloodBank.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/registeredusers")
public class RegisteredUserController {
    @Autowired
    private RegisteredUserService regUserService;
    @GetMapping("/all")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<List<RegistredUser>> getAllUsers(){
        List<RegistredUser> users = regUserService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/findByEmail/{email}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<RegistredUser> getUserByEmail(@PathVariable("email") String email){
       RegistredUser user = regUserService.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findIdByEmail/{email}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Integer> getUserIdByEmail(@PathVariable("email") String email){
        RegistredUser user = regUserService.findByEmail(email);
        return  new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RegistredUser> getUserById(@PathVariable("id") Integer id){
        RegistredUser user = regUserService.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<RegistredUser> addRegisteredUser(@RequestBody RegistredUserDto user) {
        RegistredUser newUser = regUserService.addRegisteredUser(user);
        if(newUser == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RegistredUserDto dto = new RegistredUserDto(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<RegistredUser> updateRegisteredUser(@RequestBody RegistredUser user) {
        RegistredUser newUser = regUserService.updateRegisteredUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/searchRegisteredUser/{searchInput}")
    public ResponseEntity<List<RegistredUser>> search(@PathVariable String searchInput){
        List<RegistredUser> list = regUserService.search(searchInput);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
