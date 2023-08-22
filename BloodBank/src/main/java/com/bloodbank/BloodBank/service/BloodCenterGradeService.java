package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.BloodCenterGrade;
import com.bloodbank.BloodBank.model.Visits;
import com.bloodbank.BloodBank.model.dto.BloodCenterGradeDto;
import com.bloodbank.BloodBank.repository.BloodCenterGradeRepository;
import com.bloodbank.BloodBank.repository.VisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodCenterGradeService {
    @Autowired
    private BloodCenterGradeRepository bloodCenterGradeRepository;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private BloodCenterSevice bloodCenterSevice;

    @Autowired
    private VisitsRepository visitsRepository;

    public BloodCenterGrade gradeBloodCenter(BloodCenterGradeDto dto){
        BloodCenterGrade grade = new BloodCenterGrade(
                dto.getId(),
                registeredUserService.findOne(dto.getUserId()),
                bloodCenterSevice.findOne(dto.getBloodCenterId()),
                dto.getGrade()
        );

        bloodCenterGradeRepository.save(grade);

        return grade;
    }

    public boolean canGrade(Integer bloodCenterId, Integer userId){
        List<Visits> v = visitsRepository.getVisitsByBloodCenterAndUserIds(bloodCenterId, userId);
        if (v.isEmpty()){
            return false;
        }

        return true;
    }
}
