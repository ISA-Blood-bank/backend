package com.bloodbank.BloodBank;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.Role;
import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.model.enums.Gender;
import com.bloodbank.BloodBank.repository.AddressRepository;
import com.bloodbank.BloodBank.service.RegisteredUserService;
import org.hibernate.usertype.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileUpdateTest {
    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private AddressRepository addressRepository;

    @Before
    public void setUp() throws Exception {
        RegistredUser user = new RegistredUser(1, "Marko", "Markovic", "12", Gender.MALE, "marko@gmail.com", "123", addressRepository.findById(1).get(), "student", "school", 3, Category.REGULAR, 0, "1234", true, null, null, 1);
        registeredUserService.save(user);
        }

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void appointmentBecomesUnavailable() throws Throwable {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future1 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("--------Startovan Thread 1");
                RegistredUser updateUSer = registeredUserService.findByEmail("marko@gmail.com");
                updateUSer.setName("Teodor");
                try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
                    registeredUserService.updateRegisteredUser(updateUSer);// bacice ObjectOptimisticLockingFailureException
            }
        });
        executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("--------Startovan Thread 2");
                RegistredUser updateUSer = registeredUserService.findByEmail("marko@gmail.com");
                updateUSer.setSurname("Teodorovic");
                registeredUserService.updateRegisteredUser(updateUSer);
                System.out.println("--------Izmenio u Thread 2");
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
