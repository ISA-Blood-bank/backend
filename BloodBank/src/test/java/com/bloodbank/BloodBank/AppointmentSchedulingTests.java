package com.bloodbank.BloodBank;

import com.bloodbank.BloodBank.exceptions.OverlappingAppointmentException;
import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.service.AppointmentService;
import com.bloodbank.BloodBank.service.BloodCenterSevice;
import com.bloodbank.BloodBank.service.QuestionnaireService;
import com.bloodbank.BloodBank.service.RegisteredUserService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentSchedulingTests {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private BloodCenterSevice bloodCenterSevice;

    @Before
    public void setUp() throws Exception {
        //appointmentService.save(new Appointment(1, LocalDateTime.now().plusDays(10), 1, true, null, null, 0));
        //appointmentService.save(new Appointment(2, LocalDateTime.now().plusDays(15), 1, true, null, null, 0));
        //appointmentService.save(new Appointment(3, LocalDateTime.now().plusDays(16), 1, true, null, null, 0));
        //appointmentService.save(new Appointment(4, LocalDateTime.now().plusDays(17), 1, true, null, null, 0));
        //appointmentService.save(new Appointment(5, LocalDateTime.now().plusDays(18), 1, true, null, null, 0));
        //RegistredUser user = new RegistredUser(1, "Tea", "Teic", "11", Gender.FEMALE, "tea@gmail.com", "123", null, "student", "ftn", 0, Category.REGULAR, 0, "12345");
        //registeredUserService.save(user);
        //questionnaireService.save(new Questionnaire(1, true, true, true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true, LocalDateTime.now().minusDays(3), user));
    }
    @Test(expected = OverlappingAppointmentException.class)
    public void overlappingAppointmentsUserAndAdmin() throws Throwable {
        Appointment appointment1 = new Appointment(1, LocalDateTime.now().plusDays(2), 1, true, bloodCenterSevice.findOne(1), null, 1);
        Appointment appointment2 = new Appointment(1, LocalDateTime.now().plusDays(2), 1, true, bloodCenterSevice.findOne(1), null, 1);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("Startovan Thread 1");
                    try { Thread.sleep(3000); } catch (InterruptedException ex) {}

                    appointmentService.newPredefinedAppointment(appointment1);// bacice OverlappingAppointmentException
                } /*catch (Throwable t) {
					System.out.println("Exception in Thread 1: " + t.getClass());
					throw t;
				}*/
                catch (JpaSystemException e){
                    try { Thread.sleep(2000); } catch (InterruptedException ex) {}
                    appointmentService.newPredefinedAppointment(appointment1);
                }
            }
        });
        Future<?> future2 = executor.submit(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                try {
                    System.out.println("Startovan Thread 2");

                    appointmentService.scheduleAppointment(appointment2);
                } catch (Throwable t) {
                    System.out.println("Exception in Thread 2: " + t.getClass());
                    throw t;
                }
				/*catch(JpaSystemException e){
					try { Thread.sleep(2000); } catch (InterruptedException ex) {}
					appointmentService.scheduleAppointment(appointment2);
				}*/
            }
        });
        try {
            future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
            future2.get();
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }
}
