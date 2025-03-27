package com.example.demo.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendInfo(String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("eveningnightforest@gmail.com");
        message.setSubject(subject);
        message.setText(content);
        emailSender.send(message);
    }
}
