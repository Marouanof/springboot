package com.example.demo.Services;

import com.example.demo.abstracts.DepartmentService;
import com.example.demo.dtos.DepartmentCreate;
import com.example.demo.dtos.DepartmentUpdate;
import com.example.demo.entities.Department;
import com.example.demo.repositories.DepartmentRepo;
import com.example.demo.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;
    @Override
    public Department findOne(UUID id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound
                        ("Department"+ id +"not found"));
        return department;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public void deleteOne(UUID id) {
        departmentRepo.deleteById(id);
    }

    @Override
    public Department createOne(DepartmentCreate department) {
        Department department1 = new Department();
        department1.setName(department.name());
        departmentRepo.save(department1);
        return departmentRepo.save(department1);
    }
    @Override
    public Department updateOne(UUID departmentId,DepartmentUpdate departmentUpdate){
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(()->CustomResponseException.ResourceNotFound("Department with id "+ departmentId +" not found."));
        department.setName(departmentUpdate.name());
        departmentRepo.save(department);
        return department;
    }
}
