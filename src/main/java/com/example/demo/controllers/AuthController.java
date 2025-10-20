package com.example.demo.controllers;

import com.example.demo.dtos.SignupRequest;
import com.example.demo.shared.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse<String>> signup(@RequestBody SignupRequest signupRequest) {
        System.out.println(signupRequest.employeeId());
        return new ResponseEntity<>(new GlobalResponse<>("Signup Successful"), HttpStatus.CREATED);
    }
}
