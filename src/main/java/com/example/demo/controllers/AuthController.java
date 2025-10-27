package com.example.demo.controllers;

import com.example.demo.Services.AuthService;
import com.example.demo.dtos.LoginRequest;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.shared.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse<String>> signup(@RequestBody SignupRequest signupRequest) {
        authService.signup(signupRequest);
        return new ResponseEntity<>(new GlobalResponse<>("Signup Successful"), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<String>> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return new ResponseEntity<>(new GlobalResponse<>(token), HttpStatus.CREATED);
    }
}
