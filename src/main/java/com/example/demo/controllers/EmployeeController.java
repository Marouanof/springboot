package com.example.demo.controllers;

import com.example.demo.abstracts.EmployeeService;
import com.example.demo.entities.Employee;
import com.example.demo.shared.CustomResponseException;
import com.example.demo.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    ArrayList<Employee> employees = new ArrayList<>();
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public ResponseEntity<GlobalResponse<ArrayList<Employee>>> findAll(){
        ArrayList<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeId){
        Employee employee = employeeService.findOne(employeeId);
        return new ResponseEntity<GlobalResponse<Employee>>(new GlobalResponse<>(employee),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid Employee employee){
        Employee employeeCreated = employeeService.createOne(employee);
        return new ResponseEntity<>(new GlobalResponse<>(employeeCreated),HttpStatus.CREATED);
    }
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId){
        employeeService.deleteOne(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> updateONe(@PathVariable UUID employeeId,@RequestBody @Valid Employee employee){
        Employee existingEmployee = employeeService.updateOne(employeeId, employee);
        return new ResponseEntity<>(new GlobalResponse<>(existingEmployee),HttpStatus.OK);
    }
}
