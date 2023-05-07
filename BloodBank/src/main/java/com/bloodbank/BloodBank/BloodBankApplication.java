package com.bloodbank.BloodBank;

import com.bloodbank.BloodBank.thread.PenaltiesThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import java.util.Arrays;

@SpringBootApplication
public class BloodBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodBankApplication.class, args);
		//System.out.println("Creathing thread" );
		//final Thread thread1 = new Thread(new PenaltiesThread());
		//thread1.start();
	}

}


