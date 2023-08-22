package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Visits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitsRepository extends JpaRepository<Visits, Integer> {
    @Query("select v from Visits v where v.bloodCenter.id = ?1 and v.user.id = ?2")
    public List<Visits> getVisitsByBloodCenterAndUserIds(Integer bloodCenterId, Integer userId);
}
