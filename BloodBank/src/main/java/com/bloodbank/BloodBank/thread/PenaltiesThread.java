package com.bloodbank.BloodBank.thread;

import com.bloodbank.BloodBank.repository.RegisteredUserRepository;
import com.bloodbank.BloodBank.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;

@Component
public class PenaltiesThread implements Runnable {

    @Autowired
    private RegisteredUserService registeredUserService ;

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "]"+ "started" );
        while (true){
            if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1){
                registeredUserService.resetPenalties();
        }
        }
    }
}
