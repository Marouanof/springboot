package com.example.demo.controllers;

import com.example.demo.abstracts.DepartmentService;
import com.example.demo.dtos.DepartmentCreate;
import com.example.demo.dtos.DepartmentUpdate;
import com.example.demo.entities.Department;
import com.example.demo.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping
    public ResponseEntity<GlobalResponse<List<Department>>> findAll() {
        return new ResponseEntity<>(new GlobalResponse<>(departmentService.findAll()), HttpStatus.OK);
    }
    @GetMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> findById(@PathVariable UUID departmentId) {
        Department department = departmentService.findOne(departmentId);
        return new ResponseEntity<>(new GlobalResponse<>(department), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<GlobalResponse<Department>> createOne(@RequestBody @Valid DepartmentCreate department) {
        Department newDepartment = departmentService.createOne(department);
        return new ResponseEntity<>(new GlobalResponse<>(newDepartment), HttpStatus.OK);
    }
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> deleteOne(@PathVariable UUID departmentId) {
        departmentService.deleteOne(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> updateOne(@PathVariable UUID departmentId, @RequestBody @Valid DepartmentUpdate department) {
        Department existingDepartment = departmentService.findOne(departmentId);
        existingDepartment.setName(department.name());
        return new ResponseEntity<>(new GlobalResponse<>(existingDepartment),HttpStatus.OK);
    }
}
