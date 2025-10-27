package com.example.demo.Services;

import com.example.demo.config.JwtHelper;
import com.example.demo.dtos.LoginRequest;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.entities.Employee;
import com.example.demo.entities.UserAccount;
import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.repositories.UserAccountRepo;
import com.example.demo.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private UserAccountRepo userAccountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    public void signup(SignupRequest signupRequest){
        Employee employee = employeeRepo.findById(signupRequest.employeeId())
                .orElseThrow(()-> CustomResponseException.ResourceNotFound("Employee with id "+ signupRequest.employeeId() +" not found."));
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(signupRequest.username());
        userAccount.setPassword(passwordEncoder.encode(signupRequest.password()));
        userAccount.setEmployee(employee);
        userAccountRepo.save(userAccount);
    }
    public String login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginRequest.username(),
                loginRequest.password())
        );
        UserAccount user = userAccountRepo.findOneByUsername(loginRequest.username())
                .orElseThrow(()-> CustomResponseException.BadCredentials());
        Map<String,Object> customsClaims = new HashMap<>();
        customsClaims.put("userId",user.getId());
        return jwtHelper.generateToken(customsClaims,user);
    }
}
