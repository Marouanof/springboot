package com.example.demo.dtos;

import com.example.demo.entities.Employee;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignupRequest (
        @NotNull(message="username is required")
        @Size(min=2,max=50,message = "min is 2 char max is 50")
        String username,
        @NotNull(message="username is required")
        @Size(min=2,max=50,message = "min is 2 char max is 50")
        String password,
        @NotNull(message="employee id is required")
        String employeeId
){}
