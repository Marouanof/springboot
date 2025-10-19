package com.example.demo.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LeaveRequestCreate (
    @NotNull(message="Reason is required")
    @Size(min=2, max = 100,message="min is 2 characters and max is 50 characters")
    String reason,
    @NotNull(message = "Start Date is required")
    @FutureOrPresent(message="Start Date should be now or in the future.")
    LocalDate startDate,
    @NotNull(message="End date is required")
    @FutureOrPresent(message="End date should be now or in the future.")
    LocalDate endDate
){
}
