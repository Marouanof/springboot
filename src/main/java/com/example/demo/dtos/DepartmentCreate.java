package com.example.demo.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DepartmentCreate(
        @NotNull(message="name is required")
        String name
)
{}
