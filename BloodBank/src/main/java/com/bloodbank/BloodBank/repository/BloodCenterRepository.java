package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Blood;
import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.RegistredUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BloodCenterRepository extends JpaRepository<BloodCenter, Integer> {

    @Query("select b from BloodCenter b where b.averageScore > 0 and b.averageScore < 10 ")
    public List<BloodCenter> findByAverageScoreLike(PageRequest pageable);

}
