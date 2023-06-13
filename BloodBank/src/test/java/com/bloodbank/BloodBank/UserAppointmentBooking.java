package com.bloodbank.BloodBank;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.service.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAppointmentBooking {

    @Autowired
    private AppointmentService appointmentService;


    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void oneUserSchedulingAppointment() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                Appointment appointment = appointmentService.getById(6);
                try { Thread.sleep(5000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                try {
                    appointmentService.scheduleAppointment(appointment);// bacice ObjectOptimisticLockingFailureException
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 2");
                try {
                    Appointment appointment = appointmentService.getById(6);
                    appointmentService.scheduleAppointment(appointment);
                    System.out.println("Zakazao Thread 2");
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        try {
            future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

}


