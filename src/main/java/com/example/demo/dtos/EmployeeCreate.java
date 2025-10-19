package com.example.demo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;

public record EmployeeCreate (
    @NotNull(message="first name is required")
    String firstname,
    @NotNull(message="last name is required")
    String lastname,
    @NotNull(message="email is required")
    @Email(message = "email is invalid")
    String email,
    @NotNull(message="phoneNumber is required")
    String phoneNumber,
    @NotNull(message="hireDate is required")
    @PastOrPresent(message="hireDate can't be in the future")
    LocalDate hireDate,
    @NotNull(message="position is required")
    String position,
    @NotNull(message="department id is required")
    UUID department_id
){}
