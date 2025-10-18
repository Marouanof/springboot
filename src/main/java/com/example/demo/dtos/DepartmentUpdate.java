package com.example.demo.dtos;

import jakarta.validation.constraints.NotNull;

public record DepartmentUpdate (
        @NotNull(message="name is required")
        String name
) {}
