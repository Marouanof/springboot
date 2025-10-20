package com.example.demo.Services;

import com.example.demo.abstracts.AuthService;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.entities.UserAccount;
import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.repositories.UserAccountRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private UserAccountRepo userAccountRepo;
    @Override
    public void signup(SignupRequest signupRequest){
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(signupRequest.username());
        userAccount.setPassword(signupRequest.password());
    }
}
