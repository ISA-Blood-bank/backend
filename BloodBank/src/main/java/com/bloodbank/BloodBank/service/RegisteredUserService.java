package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.*;
import com.bloodbank.BloodBank.model.dto.RegistredUserDto;
import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.registration.ConfirmationToken;
import com.bloodbank.BloodBank.repository.AddressRepository;
import com.bloodbank.BloodBank.repository.ConfirmationTokenRepository;
import com.bloodbank.BloodBank.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository regUserRep;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
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
        if(jmbgNotUnique(registredUserDto) || emailNotUnique(registredUserDto) || incorrectPassword(registredUserDto)){
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
        RegistredUser saved = regUserRep.save(registredUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), saved);
        confirmationTokenService.save(confirmationToken);

        String link = "http://localhost:8080/auth/confirm?token=" + token;
        emailService.send(
                saved.getEmail(),
                buildEmail(saved.getName(), link));

        return saved;
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

    private boolean jmbgNotUnique(RegistredUserDto registredUser){
        for(RegistredUser ru: regUserRep.findAll()){
            if(ru.getJmbg().equals(registredUser.getJmbg())){
                return true;
            }
        }
        return false;
    }

    private boolean emailNotUnique(RegistredUserDto registredUser){
        boolean ret = false;
        for(RegistredUser ru: regUserRep.findAll()){
            if(ru.getEmail().equals(registredUser.getEmail())){
                if(!ru.isEnabled()){
                    if(confirmationTokenRepository.findByUser(ru.getId()).getExpiresAt().isAfter(LocalDateTime.now())) {
                        ret = true;
                    }else{
                        ret = false;
                    }
                }else {
                    ret = true;
                }
            }
        }
        return ret;
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

    private String buildEmail(String name, String link) {
        return "Hello " + name + "! Confirm your account by clicking on link: " + link;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenService.save(confirmationToken);
        RegistredUser confirmedUser = confirmationToken.getUser();
        confirmedUser.setEnabled(true);
        regUserRep.save(confirmedUser);
        return "confirmed";
    }

    public RegistredUser save(RegistredUser registredUser){
        return regUserRep.save(registredUser);
    }
}
