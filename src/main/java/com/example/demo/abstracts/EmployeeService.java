package com.example.demo.abstracts;

import com.example.demo.entities.Employee;

import java.util.ArrayList;
import java.util.UUID;

public interface EmployeeService {
    Employee findOne(UUID employeeId);
    ArrayList<Employee> findAll();
    Employee createOne(Employee employee);
    void deleteOne(UUID employeeId);
    Employee updateOne(UUID employeeId, Employee employee);
}
