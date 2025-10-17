package com.example.demo.controllers;

import com.example.demo.entities.Employee;
import com.example.demo.shared.CustomResponseException;
import com.example.demo.shared.GlobalResponse;
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
    public ResponseEntity<GlobalResponse<ArrayList<Employee>>> findAll(){
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> findOne(@PathVariable UUID employeeId){
        Employee employee = employees.stream()
                .filter(em -> em.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(()->CustomResponseException.ResourceNotFound("Employee with id "+ employeeId +" not found."));
        return new ResponseEntity<Employee>(employee,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid Employee employee){
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employees.add(employee);
        return new ResponseEntity<>(new GlobalResponse<>(employee),HttpStatus.CREATED);
    }
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId){
        Optional<Employee> employee = employees.stream()
                        .filter(em -> em.getId().equals(employeeId))
                .findFirst();
        if (employee.isPresent()) {
            employees.remove(employee.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> updateONe(@PathVariable UUID employeeId,@RequestBody @Valid Employee employee){
        Employee existingEmployee = employees.stream()
                .filter(em -> em.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(()->CustomResponseException.ResourceNotFound("Employee with id "+ employeeId +" not found."));
        existingEmployee.setFirstname(employee.getFirstname());
        existingEmployee.setLastname(employee.getLastname());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setHireDate(employee.getHireDate());
        existingEmployee.setDepartmentId(existingEmployee.getDepartmentId());
        return new ResponseEntity<>(new GlobalResponse<>(existingEmployee),HttpStatus.OK);
    }
}
