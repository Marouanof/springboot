package com.example.demo.abstracts;

import com.example.demo.dtos.DepartmentCreate;
import com.example.demo.dtos.DepartmentUpdate;
import com.example.demo.entities.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    Department findOne(UUID id);
    List<Department> findAll();
    void deleteOne(UUID id);
    Department createOne(DepartmentCreate department);
    Department updateOne(UUID id, DepartmentUpdate departmentUpdate);
}
