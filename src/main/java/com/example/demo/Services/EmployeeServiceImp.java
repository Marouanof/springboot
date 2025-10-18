package com.example.demo.Services;

import com.example.demo.abstracts.EmployeeService;
import com.example.demo.entities.Employee;
import com.example.demo.shared.CustomResponseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
@Service
public class EmployeeServiceImp implements EmployeeService {
    ArrayList<Employee> employees = new ArrayList<>();
    @Override
    public Employee findOne(UUID employeeId) {
        Employee employee = employees.stream()
                .filter(em -> em.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(()-> CustomResponseException.ResourceNotFound("Employee with id "+ employeeId +" not found."));
        return employee;
    }
    @Override
    public ArrayList<Employee> findAll() {
        return employees;
    }
    @Override
    public void deleteOne(UUID employeeId){
        Optional<Employee> employee = employees.stream()
                .filter(em -> em.getId().equals(employeeId))
                .findFirst();
        if (employee.isPresent()) {
            employees.remove(employee.get());
        }
    }
    @Override
    public Employee updateOne(UUID employeeId, Employee employee) {
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
        return existingEmployee;
    }
    @Override
    public Employee createOne(Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employees.add(employee);
        return employee;
    }
}
