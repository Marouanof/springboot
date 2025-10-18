package com.example.demo.abstracts;

import com.example.demo.dtos.EmployeeCreate;
import com.example.demo.dtos.EmployeeUpdate;
import com.example.demo.entities.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    Employee findOne(UUID employeeId);
    List<Employee> findAll();
    Employee createOne(EmployeeCreate employee);
    void deleteOne(UUID employeeId);
    Employee updateOne(UUID employeeId, EmployeeUpdate employee);
}
