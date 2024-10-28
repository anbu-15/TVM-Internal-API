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
            existingRequest.setLeaveType(leaveRequest.getLeaveType());
            existingRequest.setStartDate(leaveRequest.getStartDate());
            existingRequest.setEndDate(leaveRequest.getEndDate());
            existingRequest.setTotalDays(leaveRequest.getTotalDays());
            existingRequest.setStatus(leaveRequest.getStatus());
            existingRequest.setReasonforLeave(leaveRequest.getReasonforLeave());
            existingRequest.setDateOfRequest(leaveRequest.getDateOfRequest());
            existingRequest.setComment(leaveRequest.getComment());
            existingRequest.setReasonforRejected(leaveRequest.getReasonforRejected());
            existingRequest.setColor(leaveRequest.getColor());
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