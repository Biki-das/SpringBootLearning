package com.example.FakeCommereceApp.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommereceApp.services.EmailService;
import com.example.FakeCommereceApp.utils.ApiResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/send/emails")



public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ApiResponse<Void> sendEmail() throws IOException {
        emailService.sendEmail();
        return ApiResponse.success("Email sent", null);
    }

}
