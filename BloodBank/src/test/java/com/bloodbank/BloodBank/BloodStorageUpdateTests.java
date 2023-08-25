package com.bloodbank.BloodBank;

import com.bloodbank.BloodBank.model.*;
import com.bloodbank.BloodBank.model.dto.AdditionalInfoDto;
import com.bloodbank.BloodBank.model.enums.BloodType;
import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.model.enums.Gender;
import com.bloodbank.BloodBank.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BloodStorageUpdateTests {
    @Autowired
    private BloodService bloodService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private BloodCenterSevice bloodCenterSevice;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Test(expected = PessimisticLockingFailureException.class)
    public void testBloodStorageUpdate() throws Throwable {
        BloodCenter bc = new BloodCenter(
                1,
                "Crveni krst",
                new Address(1,"Blagoja Parovica", "1", "Novi Sad", "Srbija"),
                "opis1",
                4.6f);


        MedicalStaff ms1 = new MedicalStaff(1,
                "Miloje",
                "Milojevic",
                "1111111111",
                Gender.MALE,
                "miloje@gmail.com",
                "1234567890", new Address(2, "Melhiora Erdujheljija", "8", "Novi Sad", "Srbija") ,
                bc);

        MedicalStaff ms2 = new MedicalStaff(2,
                "Simo",
                "Simic"
                , "09876543210",
                Gender.MALE,
                "simo@gmail.com",
                "0987654321",
                new Address(3, "Ilariona Ruvarca", "7", "Novi Sad", "Srbija"),
                bc);


        Appointment a1 = new Appointment(-1, LocalDateTime.now().plusHours(1), 30.0f, false, bc, ms1);
        Appointment a2 = new Appointment(-1, LocalDateTime.now().plusDays(1), 30.0f, false, bc, ms2);



        RegistredUser r1 = new RegistredUser(
                1,
                "Mila",
                "Milic",
                "77777777",
                Gender.FEMALE,
                "mila@gmail.com",
                "123",
                new Address(4, "Fruskogorska", "8", "Novi Sad", "Srbija"),
                "student",
                "ftn",
                40.0f,
                Category.SILVER,
                0,
                "069999999",
                60.0f);

        RegistredUser r2 = new RegistredUser(
                2,
                "Jovana",
                "Jovanovic",
                "77767777",
                Gender.FEMALE,
                "jovana@gmail.com",
                "123",
                new Address(5, "Partizanska", "8", "Novi Sad", "Srbija"),
                "student",
                "ftn",
                40.0f,
                Category.SILVER,
                0,
                "069998999",
                60.0f);



        ScheduledAppointment s1 = new ScheduledAppointment(1, a1, r1, false, false);
        ScheduledAppointment s2 = new ScheduledAppointment(2, a2, r2, false, false);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovana nit:     1");
                AdditionalInfoDto dto = new AdditionalInfoDto(
                        1,
                        1,
                        BloodType.A_NEG,
                        1,
                        true,
                        true,
                        false,
                        "nesto",
                        "h",
                        true,
                        true,
                        "a",
                        "b",
                        "c",
                        "kesa",
                        "nista",
                        "nista",
                        true,
                        1);


                bloodService.addBloodToCentre(dto);

            }
        });

        Future<?> future2 = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Startovana nit:     2");
                AdditionalInfoDto dto = new AdditionalInfoDto(
                        2,
                        2,
                        BloodType.A_NEG,
                        2,
                        false,
                        true,
                        false,
                        "nesto1",
                        "h",
                        true,
                        true,
                        "a1",
                        "b1",
                        "c1",
                        "kesa1",
                        "nista1",
                        "nista1",
                        true,
                        2);
                try {Thread.sleep(40); } catch (InterruptedException e) {}

               bloodService.addBloodToCentre(dto);


            }
        });

        try {
            future2.get();
        } catch (ExecutionException e){
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas PessimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
