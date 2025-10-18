package com.example.demo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record EmployeeUpdate (
        @NotNull(message="first name is required")
        String firstname,
        @NotNull(message="last name is required")
        String lastname,
        @NotNull(message="phoneNumber is required")
        String phoneNumber,
        LocalDate hireDate,
        @NotNull(message="position is required")
        String position
){}
