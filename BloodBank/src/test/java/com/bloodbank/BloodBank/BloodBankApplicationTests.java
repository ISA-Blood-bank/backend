package com.bloodbank.BloodBank;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.Questionnaire;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.model.enums.Gender;
import com.bloodbank.BloodBank.service.AppointmentService;
import com.bloodbank.BloodBank.service.QuestionnaireService;
import com.bloodbank.BloodBank.service.RegisteredUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BloodBankApplicationTests {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RegisteredUserService registeredUserService;

	@Autowired
	private QuestionnaireService questionnaireService;

	@Before
	public void setUp() throws Exception {
		appointmentService.save(new Appointment(1, LocalDateTime.now().plusDays(10), 1, true, null, null, 0));
		appointmentService.save(new Appointment(2, LocalDateTime.now().plusDays(15), 1, true, null, null, 0));
		appointmentService.save(new Appointment(3, LocalDateTime.now().plusDays(16), 1, true, null, null, 0));
		appointmentService.save(new Appointment(4, LocalDateTime.now().plusDays(17), 1, true, null, null, 0));
		appointmentService.save(new Appointment(5, LocalDateTime.now().plusDays(18), 1, true, null, null, 0));
		RegistredUser user = new RegistredUser(1, "Tea", "Teic", "11", Gender.FEMALE, "tea@gmail.com", "123", null, "student", "ftn", 0, Category.REGULAR, 0, "12345");
		registeredUserService.save(user);
		questionnaireService.save(new Questionnaire(1, true, true, true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true, LocalDateTime.now().minusDays(3), user));
	}

	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testOptimisticLockingScenario() throws Throwable {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 1");
				try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
				appointmentService.scheduleAppointment(1);// bacice ObjectOptimisticLockingFailureException
			}
		});
		executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 2");
				/*
				 * prvi ce izvrsiti izmenu i izvrsi upit:
				 * Hibernate:
				 *     update
				 *         product
				 *     set
				 *         name=?,
				 *         origin=?,
				 *         price=?,
				 *         version=?
				 *     where
				 *         id=?
				 *         and version=?
				 *
				 * Moze se primetiti da automatski dodaje na upit i proveru o verziji
				 */
				appointmentService.scheduleAppointment(1);
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
