package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.LeaveRequest;
import com.tvm.internal.edit.repo.LeaveRequestRepo;
import com.tvm.internal.edit.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepo.findAll();
    }

    @Override
    public Optional<LeaveRequest> getLeaveRequestById(Long id) {
        return leaveRequestRepo.findById(id);
    }

    @Override
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepo.save(leaveRequest);
    }

    @Override
    public Optional<LeaveRequest> updateLeaveRequest(Long id, LeaveRequest leaveRequest) {
        return leaveRequestRepo.findById(id).map(existingRequest -> {
            existingRequest.setStatus(leaveRequest.getStatus());
//            existingRequest.setComment(leaveRequest.getComment());
            return leaveRequestRepo.save(existingRequest);
        });
    }

    @Override
    public boolean deleteLeaveRequest(Long id) {

        if (leaveRequestRepo.existsById(id)) {
            leaveRequestRepo.deleteById(id);
            return true;
        }
        return false;
    }
}