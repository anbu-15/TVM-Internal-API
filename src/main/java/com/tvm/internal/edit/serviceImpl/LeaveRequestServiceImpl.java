package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.LeaveRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface LeaveRequestServiceImpl {

    List<LeaveRequest> getAllLeaveRequests();

    // Retrieve a specific leave request by its ID
    Optional<LeaveRequest> getLeaveRequestById(Long id);

    // Create a new leave request
    LeaveRequest createLeaveRequest(LeaveRequest leaveRequest);

    // Update an existing leave request by its ID
    LeaveRequest updateLeaveRequest(Long id, LeaveRequest leaveRequest);

    // Delete a leave request by its ID
    void deleteLeaveRequest(Long id);

}
