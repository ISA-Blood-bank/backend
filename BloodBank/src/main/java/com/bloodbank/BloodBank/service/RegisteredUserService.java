package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.*;
import com.bloodbank.BloodBank.model.dto.RegistredUserDto;
import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.repository.AddressRepository;
import com.bloodbank.BloodBank.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository regUserRep;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;
    Address identical= new Address();

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
    public RegistredUser addRegisteredUser(RegistredUserDto registredUserDto){
        if(jmbgOrEmailNotUnique(registredUserDto) || incorrectPassword(registredUserDto)){
            return null;
        }
        RegistredUser registredUser = new RegistredUser(registredUserDto.getId(), registredUserDto.getName(), registredUserDto.getSurname(),
                registredUserDto.getJmbg(), registredUserDto.getGender(), registredUserDto.getEmail(), passwordEncoder.encode(registredUserDto.getPassword1()), registredUserDto.getAddress(),
                registredUserDto.getOccupation(),registredUserDto.getJobOrSchoolInfo(), registredUserDto.getPoints(), registredUserDto.getCategory(), registredUserDto.getPenalties(), registredUserDto.getPhone());
        if(addressExists(registredUser)){
            registredUser.setAddress(identical);
        }else{
            Address addresWithId = addressRepository.save(registredUser.getAddress());
            registredUser.setAddress(addresWithId);
        }
        registredUser.setCategory(Category.REGULAR);
        registredUser.setPoints((float)0.0);
        registredUser.setPenalties(0);
        registredUser.setEnabled(false);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        registredUser.setLastPasswordResetDate(now);
        List<Role> roles = roleService.findByName("ROLE_USER");
        registredUser.setRoles(roles);
        return regUserRep.save(registredUser);
    }
    public void remove(Integer id){
        regUserRep.deleteById(id);
    }

    private boolean addressExists(RegistredUser ru){
        boolean found=false;
        for(Address a :  addressRepository.findAll())
        {
            if(a.getCity().equals(ru.getAddress().getCity()) && a.getCountry().equals(ru.getAddress().getCountry()) &&
                    a.getNumber().equals(ru.getAddress().getNumber())&& a.getStreet().equals(ru.getAddress().getStreet()))
            {
                identical=a;
                found=true;
                return found;

            }
        }
        return found;
    }

    private boolean jmbgOrEmailNotUnique(RegistredUserDto registredUser){
        for(RegistredUser ru: regUserRep.findAll()){
            if(ru.getEmail().equals(registredUser.getEmail()) || ru.getJmbg().equals(registredUser.getJmbg())){
                return true;
            }
        }
        return false;
    }

    private boolean incorrectPassword(RegistredUserDto registredUserDto){
        if(!registredUserDto.getPassword1().equals(registredUserDto.getPassword2())){
            return true;
        }
        return false;
    }

    public List<RegistredUser> search(String searchInput){
        String[] inputs = searchInput.split(" ");
        List<RegistredUser> list = regUserRep.search(inputs[0], inputs[1]);
        return list;
    }

    public RegistredUser findByEmail(String email){
       return regUserRep.findByEmail(email);
    }
}
