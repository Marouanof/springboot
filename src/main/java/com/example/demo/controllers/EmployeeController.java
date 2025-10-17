package com.example.demo.controllers;

import com.example.demo.entities.Employee;
import com.example.demo.shared.CustomResponseException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<>(
            List.of(new Employee(
                    UUID.randomUUID(),
                    "John",
                    "Doe",
                    "john@email.com",
                    "+123456789",
                    LocalDate.of(2024,2,25),
                    "Software Engineer",
                    UUID.randomUUID()
                    ),
                    new Employee(
                            UUID.randomUUID(),
                            "Donald",
                            "Trump",
                            "trump@email.com",
                            "+123456789",
                            LocalDate.of(2024,2,25),
                            "President",
                            UUID.randomUUID())
    ));
    @GetMapping
    public ResponseEntity<ArrayList<Employee>> findAll(){
        return new ResponseEntity<ArrayList<Employee>>(employees, HttpStatus.OK);
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<Optional<Employee>> findOne(@PathVariable UUID employeeId){
        Optional<Employee> employee = employees.stream()
                .filter(em -> em.getId().equals(employeeId)).findFirst();
        if(employee.isEmpty()){
            throw CustomResponseException.ResourceNotFound("Employee with id "+employeeId+" not found.");
        }
        return new ResponseEntity<Optional<Employee>>(employee,HttpStatus.OK);
    }
    @PostMapping
    public void createOne(@RequestBody @Valid Employee employee){
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employees.add(employee);
    }
    @DeleteMapping("/{employeeId}")
    public void deleteOne(@PathVariable UUID employeeId){
        Optional<Employee> employee = employees.stream()
                        .filter(em -> em.getId().equals(employeeId))
                .findFirst();
        if (employee.isPresent()) {
            employees.remove(employee.get());
        }
    }
    @PutMapping("/{employeeId}")
    public Employee updateONe(@PathVariable UUID employeeId,@RequestBody @Valid Employee employee){
        Optional<Employee> existingEmployee = employees.stream()
                .filter(em -> em.getId().equals(employeeId))
                .findFirst();
        if(existingEmployee.isPresent()){
            employee.setFirstname(existingEmployee.get().getFirstname());
            employee.setLastname(existingEmployee.get().getLastname());
            employee.setEmail(existingEmployee.get().getEmail());
            employee.setPhoneNumber(existingEmployee.get().getPhoneNumber());
            employee.setPosition(existingEmployee.get().getPosition());
            employee.setHireDate(existingEmployee.get().getHireDate());
            employee.setDepartmentId(existingEmployee.get().getDepartmentId());
        }
        return employee;
    }
}
