package com.example.demo.Services;

import com.example.demo.abstracts.LeaveRequestService;
import com.example.demo.dtos.LeaveRequestCreate;
import com.example.demo.entities.Employee;
import com.example.demo.entities.LeaveRequest;
import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.repositories.LeaveRequestRepo;
import com.example.demo.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LeaveRequestServiceImp implements LeaveRequestService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private LeaveRequestRepo leaveRequestRepo;
    public List<LeaveRequest> findAll(UUID id) {
        return leaveRequestRepo.findAllByEmployeeId(id);
    }

    @Override
    public LeaveRequest createOne(LeaveRequestCreate leaveRequest, UUID employeeId) {
        Employee existingEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(()-> CustomResponseException.ResourceNotFound("Employee with id "+ employeeId +" not found."));
        LeaveRequest newLeaveRequest = new LeaveRequest();
        newLeaveRequest.setReason(leaveRequest.reason());
        newLeaveRequest.setStartDate(leaveRequest.startDate());
        newLeaveRequest.setEndDate(leaveRequest.endDate());
        newLeaveRequest.setStatus("PENDING");
        newLeaveRequest.setEmployee(existingEmployee);
        leaveRequestRepo.save(newLeaveRequest);
        return newLeaveRequest;
    }

    @Override
    public LeaveRequest updateOne(UUID id, LeaveRequest leaveRequest) {
        return null;
    }

    @Override
    public void deleteOne(UUID id) {

    }

    @Override
    public LeaveRequest findOne(UUID id) {
        return null;
    }
}
