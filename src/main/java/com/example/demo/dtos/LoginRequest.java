package com.example.demo.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest (
        @NotNull(message="username is required")
        @Size(min=2,max=50,message = "min is 2 char max is 50")
        String username,
        @NotNull(message="username is required")
        @Size(min=2,max=50,message = "min is 2 char max is 50")
        String password
){}
