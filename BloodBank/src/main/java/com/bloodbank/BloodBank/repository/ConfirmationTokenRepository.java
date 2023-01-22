package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.registration.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);

    @Query("select t from ConfirmationToken t where t.user.id = ?1")
    ConfirmationToken findByUser(int user_id);
}
