package com.example.demo.abstracts;


import com.example.demo.dtos.LeaveRequestCreate;
import com.example.demo.entities.LeaveRequest;

import java.util.List;
import java.util.UUID;

public interface LeaveRequestService {
    LeaveRequest findOne(UUID id);
    List<LeaveRequest> findAll(UUID id);
    LeaveRequest createOne(LeaveRequestCreate leaveRequest, UUID id);
    LeaveRequest updateOne(UUID id, LeaveRequest leaveRequest);
    void deleteOne(UUID id);
}
