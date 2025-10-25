package com.example.demo.Services;

import com.example.demo.dtos.SignupRequest;
import com.example.demo.entities.Employee;
import com.example.demo.entities.UserAccount;
import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.repositories.UserAccountRepo;
import com.example.demo.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private UserAccountRepo userAccountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void signup(SignupRequest signupRequest){
        Employee employee = employeeRepo.findById(signupRequest.employeeId())
                .orElseThrow(()-> CustomResponseException.ResourceNotFound("Employee with id "+ signupRequest.employeeId() +" not found."));
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(signupRequest.username());
        userAccount.setPassword(passwordEncoder.encode(signupRequest.password()));
        userAccount.setEmployee(employee);
        userAccountRepo.save(userAccount);
    }
}
