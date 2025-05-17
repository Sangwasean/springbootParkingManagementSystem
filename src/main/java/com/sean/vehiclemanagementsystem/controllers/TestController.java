package com.mikepn.vehiclemanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/send-email")
    public String sendTestEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("kdsean11@gmail.com");
        message.setSubject("Test Email");
        message.setText("This is a test email from Spring Boot.");
        mailSender.send(message);
        return "Test email sent!";
    }
}