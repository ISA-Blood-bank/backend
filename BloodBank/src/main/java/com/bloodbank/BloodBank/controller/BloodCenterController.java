package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.Blood;
import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.dto.BloodCenterDto;
import com.bloodbank.BloodBank.service.BloodCenterSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/bloodCenters")
public class BloodCenterController {

    @Autowired
    private BloodCenterSevice bloodCenterSevice;

    public BloodCenterController(BloodCenterSevice bloodCenterSevice){
        this.bloodCenterSevice = bloodCenterSevice;
    }
    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BloodCenter>> findAll(){

        List<BloodCenter> bloodCenters = bloodCenterSevice.findAll();

        List<BloodCenterDto> bloodCenterDtos = new ArrayList<>();
        for (BloodCenter bc : bloodCenters){
            bloodCenterDtos.add(new BloodCenterDto(bc));
        }

        return new ResponseEntity<>(bloodCenters, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<BloodCenter> addBloodCenter(@RequestBody BloodCenter bc) {
        BloodCenter bcnew = bloodCenterSevice.addBloodCenter(bc);
        return new ResponseEntity<>(bcnew, HttpStatus.CREATED);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<BloodCenter> findOne(@PathVariable("id") Integer id){
        BloodCenter bc = bloodCenterSevice.findOne(id);
        return  new ResponseEntity<>(bc, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<BloodCenter> update(@RequestBody BloodCenter bc){
        BloodCenter newBloodCenter = bloodCenterSevice.update(bc);
        return new ResponseEntity<BloodCenter>(newBloodCenter, HttpStatus.OK);
    }


    @GetMapping("/searchBloodCenter/{searchInput}")
    public ResponseEntity<List<BloodCenter>> search(@PathVariable String searchInput){
        List<BloodCenter> list = bloodCenterSevice.search(searchInput);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/filterBloodCenter/{input}/{input2}")
    public ResponseEntity<List<BloodCenter>> filter(@PathVariable String input, @PathVariable String input2){
        System.out.println("ovde je"+input+"i"+input2);
        List<BloodCenter> list = bloodCenterSevice.filter(input,input2);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/sorted/{page}/{size}/{sortList}/{order}")
    public ResponseEntity<List<BloodCenter>> findAllSortedAndFiltered(@PathVariable Integer page,
                                                                      @PathVariable Integer size,
                                                                      @PathVariable String sortList,
                                                                      @PathVariable String order

    ){
        List<BloodCenter> p = bloodCenterSevice.findAllSortedAndFiltered(page, size, sortList, order);
       //List<BloodCenter> list = p.getContent();
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @GetMapping("/getavailable/{date}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BloodCenter>> getAvailableBloodCenters(@PathVariable LocalDateTime date){
        List<BloodCenter> available = bloodCenterSevice.getAvailableBloodCenters(date);
        return new ResponseEntity<>(available, HttpStatus.OK);
    }

}
