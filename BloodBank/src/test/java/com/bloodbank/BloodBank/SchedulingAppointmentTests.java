package com.bloodbank.BloodBank;

import com.bloodbank.BloodBank.exceptions.SameMedicalStaffException;
import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.service.AppointmentService;
import com.bloodbank.BloodBank.service.MedicalStaffService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulingAppointmentTests {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @Test(expected = PessimisticLockingFailureException.class)
    public void testSchedulingAppointmentPessimistic() throws Throwable{
        MedicalStaff m1 = medicalStaffService.findById(2);
        BloodCenter bc = m1.getBloodCenter();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Appointment a1 = new Appointment(-1, LocalDateTime.now().plusHours(1), 30.0f, true, bc, m1);
        Appointment a2 = new Appointment(-1, LocalDateTime.now().plusMinutes(30), 30.0f, true, bc, m1);

        Future<?> future1 = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 1");

                appointmentService.save(a1);
            }
        });

        Future<?> future2 = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e){}
                appointmentService.save(a2);
            }
        });

        try {
            future2.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas PessimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    @Test(expected = SameMedicalStaffException.class)
    public void testSchedulingAppointmentException() throws Throwable{
        MedicalStaff m1 = medicalStaffService.findById(2);
        BloodCenter bc = m1.getBloodCenter();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Appointment a1 = new Appointment(-1, LocalDateTime.now().plusHours(1), 30.0f, true, bc, m1);
        Appointment a2 = new Appointment(-1, LocalDateTime.now().plusMinutes(30), 30.0f, true, bc, m1);

        Future<?> future1 = executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Startovan Thread 1");
                    appointmentService.save(a1);
                } catch (JpaSystemException | PessimisticLockingFailureException e) {
                    try {Thread.sleep(3000);} catch (InterruptedException ex){}
                    appointmentService.save(a1);
                    System.out.println("Thread 1 dodao");
                }
            }
        });

        Future<?> future2 = executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Startovan Thread 2");
                    appointmentService.save(a2);
                } catch (JpaSystemException | PessimisticLockingFailureException e) {
                    try {Thread.sleep(3000);} catch (InterruptedException ex){}
                    appointmentService.save(a2);
                    System.out.println("Thread 2 dodao");
                }
            }
        });

        try {
            future1.get();
            future2.get();
        } catch (ExecutionException e){
            System.out.println("Exception from thread " + e.getCause().getClass());
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }




}
