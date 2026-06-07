package com.example.FakeCommereceApp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommereceApp.services.EmailService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/send/emails")



public class EmailController {
    private final EmailService emailService;
    
    @PostMapping
    public void sendEmail() {
        try {
            emailService.sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    
}
