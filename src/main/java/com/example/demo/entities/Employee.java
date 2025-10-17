package com.example.demo.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Employee {
    private UUID id;
    @NotNull(message="first name is required")
    private String firstname;
    @NotNull(message="last name is required")
    private String lastname;
    @NotNull(message="email is required")
    @Email(message = "email is invalid")
    private String email;
    @NotNull(message="phoneNumber is required")
    private String phoneNumber;
    @NotNull(message="hireDate is required")
    @PastOrPresent(message="hireDate can't be in the future")
    private LocalDate hireDate;
    @NotNull(message="position is required")
    private String position;
    private UUID departmentId;
}
