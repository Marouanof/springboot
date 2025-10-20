package com.example.demo.abstracts;

import com.example.demo.dtos.SignupRequest;

public interface AuthService {
    void signup(SignupRequest signupRequest);
}
