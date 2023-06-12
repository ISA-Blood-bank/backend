package com.bloodbank.BloodBank.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;

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

    @Async
    public void sendAppointmentScheduledMail(String to, String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom("isa.projekat23@gmail.com");
            helper.setTo(to);
            helper.setText(email);
            helper.setSubject("Potvrda zakazanog termina");

            // Generate the QR code image
            QRCodeWriter qrWriter = new QRCodeWriter();
            BitMatrix qrMatrix = qrWriter.encode("proba", BarcodeFormat.QR_CODE, 200, 200);
            ByteArrayOutputStream qrOut = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(qrMatrix, "PNG", qrOut);
            byte[] qrBytes = qrOut.toByteArray();

            // Attach the QR code image to the email message
            DataSource dataSource = new ByteArrayDataSource(qrBytes, "image/png");
            helper.addAttachment("qr-code.png", dataSource);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
