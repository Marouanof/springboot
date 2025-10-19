package com.example.demo.controllers;

import com.example.demo.abstracts.EmployeeService;
import com.example.demo.abstracts.LeaveRequestService;
import com.example.demo.dtos.EmployeeCreate;
import com.example.demo.dtos.EmployeeUpdate;
import com.example.demo.dtos.LeaveRequestCreate;
import com.example.demo.entities.Employee;
import com.example.demo.entities.LeaveRequest;
import com.example.demo.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private LeaveRequestService leaveRequestService;
    @GetMapping
    public ResponseEntity<GlobalResponse<List<Employee>>> findAll(){
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeId){
        Employee employee = employeeService.findOne(employeeId);
        return new ResponseEntity<GlobalResponse<Employee>>(new GlobalResponse<>(employee),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid EmployeeCreate employee){
        Employee employeeCreated = employeeService.createOne(employee);
        return new ResponseEntity<>(new GlobalResponse<>(employeeCreated),HttpStatus.CREATED);
    }
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId){
        employeeService.deleteOne(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> updateONe(@PathVariable UUID employeeId,@RequestBody @Valid EmployeeUpdate employee){
        Employee existingEmployee = employeeService.updateOne(employeeId, employee);
        return new ResponseEntity<>(new GlobalResponse<>(existingEmployee),HttpStatus.OK);
    }
    @PostMapping("/{employeeId}/leave-request")
    public ResponseEntity<GlobalResponse<LeaveRequest>> leaveRequest(@RequestBody @Valid LeaveRequestCreate leaveRequestCreate, @PathVariable UUID employeeId){
        LeaveRequest newLeaveRequest = leaveRequestService.createOne(leaveRequestCreate,employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(newLeaveRequest),HttpStatus.CREATED);
    }
    @GetMapping("/{employeeId}/leave-requests")
    public ResponseEntity<GlobalResponse<List<LeaveRequest>>> leaveRequestByEmployeeId(@PathVariable UUID employeeId){
        List<LeaveRequest> leaveRequests = leaveRequestService.findAll(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(leaveRequests),HttpStatus.OK);
    }
}
