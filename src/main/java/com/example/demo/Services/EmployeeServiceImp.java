package com.example.demo.Services;

import com.example.demo.abstracts.EmployeeService;
import com.example.demo.dtos.EmployeeCreate;
import com.example.demo.dtos.EmployeeUpdate;
import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public Employee findOne(UUID employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(()-> CustomResponseException.ResourceNotFound("Employee with id "+ employeeId +" not found."));
        return employee;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }
    @Override
    public void deleteOne(UUID employeeId){
        Optional<Employee> employee = employeeRepo.findById(employeeId);
        if (employee.isPresent()) {
            employeeRepo.deleteById(employee.get().getId());
        }
    }
    @Override
    public Employee updateOne(UUID employeeId, EmployeeUpdate employee) {
        Employee existingEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(()->CustomResponseException.ResourceNotFound("Employee with id "+ employeeId +" not found."));
        existingEmployee.setFirstname(employee.firstname());
        existingEmployee.setLastname(employee.lastname());
        existingEmployee.setPhoneNumber(employee.phoneNumber());
        existingEmployee.setPosition(employee.position());
        existingEmployee.setDepartmentId(existingEmployee.getDepartmentId());
        employeeRepo.save(existingEmployee);
        return existingEmployee;
    }
    @Override
    public Employee createOne(EmployeeCreate employeeCreate) {
        Employee employee = new Employee();
        employee.setFirstname(employeeCreate.firstname());
        employee.setLastname(employeeCreate.lastname());
        employee.setEmail(employeeCreate.email());
        employee.setPhoneNumber(employeeCreate.phoneNumber());
        employee.setPosition(employeeCreate.position());
        employee.setHireDate(employeeCreate.hireDate());
        employeeRepo.save(employee);
        return employee;
    }
}
