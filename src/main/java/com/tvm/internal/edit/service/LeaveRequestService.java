package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.LeaveRequest;
import com.tvm.internal.edit.repo.LeaveRequestRepo;
import com.tvm.internal.edit.service.LeaveRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService implements LeaveRequestServiceImpl {

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
    public LeaveRequest updateLeaveRequest(Long id, LeaveRequest leaveRequest) {
        Optional<LeaveRequest> existingRequest = leaveRequestRepo.findById(id);
        if (existingRequest.isPresent()) {
            LeaveRequest updatedRequest = existingRequest.get();
            updatedRequest.setLeaveType(leaveRequest.getLeaveType());
            updatedRequest.setStartDate(leaveRequest.getStartDate());
            updatedRequest.setEndDate(leaveRequest.getEndDate());
            updatedRequest.setTotalDays(leaveRequest.getTotalDays());
            updatedRequest.setStatus(leaveRequest.getStatus());
            updatedRequest.setReasonforLeave(leaveRequest.getReasonforLeave());
            updatedRequest.setDateOfRequest(leaveRequest.getDateOfRequest());
            updatedRequest.setComment(leaveRequest.getComment());
            updatedRequest.setReasonforRejected(leaveRequest.getReasonforRejected());
            updatedRequest.setColor(leaveRequest.getColor());
            return leaveRequestRepo.save(updatedRequest);
        }
        return null;
    }

    @Override
    public void deleteLeaveRequest(Long id) {
        leaveRequestRepo.deleteById(id);
    }
}
