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


}
