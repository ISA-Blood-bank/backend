package com.bloodbank.BloodBank;

import com.bloodbank.BloodBank.exceptions.AdminAlreadyExists;
import com.bloodbank.BloodBank.exceptions.BloodCenterAlreadyExists;
import com.bloodbank.BloodBank.model.Address;
import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.Role;
import com.bloodbank.BloodBank.model.dto.RegistredUserDto;
import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.model.enums.Gender;
import com.bloodbank.BloodBank.service.BloodCenterSevice;
import com.bloodbank.BloodBank.service.RegisteredUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminRegistration {

    @Autowired
    private RegisteredUserService registeredUserService;

    @Test(expected = AdminAlreadyExists.class)
    public void BloodCenterAlreadyExists() throws Throwable {
        Address address = new Address(1,"Sekspirova","5","Novi Sad","Srbija");

        RegistredUserDto registredUser1= new RegistredUserDto(1,"petar","petrovic","1234567", Gender.MALE,"petar@gmail.com","123","123",address);

        RegistredUserDto registredUser2= new RegistredUserDto(1,"petar","petrovic","1234567", Gender.MALE,"petar@gmail.com","123","123",address);


            ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("Started Thread 1");
                    registeredUserService.addRegisteredUser(registredUser1);//bacice custom exeption
                }
                /*catch (Throwable t) {
					System.out.println("Exception in Thread 1: " + t.getClass());
					throw t;
				}*/
                catch (JpaSystemException | CannotAcquireLockException e){
                    try { Thread.sleep(2000); } catch (InterruptedException ex) {}
                    registeredUserService.addRegisteredUser(registredUser2);
                    System.out.println("Added in Thread 1");
                }
            }
        });

        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("Started Thread 2");
                    registeredUserService.addRegisteredUser(registredUser2);
                }
              /*  catch (Throwable t) {
					System.out.println("Exception in Thread 2: " + t.getClass());
					throw t;
				}*/
                catch (JpaSystemException  | CannotAcquireLockException e){
                    try { Thread.sleep(2000); } catch (InterruptedException ex) {}
                    registeredUserService.addRegisteredUser(registredUser2);
                    System.out.println("Added in Thread 2");
                }
            }


        });

        try {
            future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
            future2.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }


}
