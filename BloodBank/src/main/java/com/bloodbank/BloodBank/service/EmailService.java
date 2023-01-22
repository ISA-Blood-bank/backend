package com.bloodbank.BloodBank.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;


    @Async
    public void send(String to, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("projekti.fax00@gmail.com");
        message.setTo(to);
        message.setText(email);
        message.setSubject("Confirm your account");

        mailSender.send(message);
    }
}
