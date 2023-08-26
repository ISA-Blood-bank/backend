package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Blood;
import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.enums.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface BloodRepository extends JpaRepository<Blood, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from Blood b where b.bloodCenter.id = ?1 and b.type = ?2")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Blood getBloodByBloodCenterIdAndBloodType(int bloodCenterId, BloodType type);

    @Query("select b from Blood b where b.bloodCenter.id = ?1")
    public List<Blood> getBloodByBloodCenterId(Integer id);
}
