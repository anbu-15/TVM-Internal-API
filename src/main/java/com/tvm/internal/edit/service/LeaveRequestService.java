package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.LeaveRequest;

import java.util.List;
import java.util.Optional;


public interface LeaveRequestService {

    List<LeaveRequest> getAllLeaveRequests();

    Optional<LeaveRequest> getLeaveRequestById(Long id);

    LeaveRequest createLeaveRequest(LeaveRequest leaveRequest);

    Optional<LeaveRequest> updateLeaveRequest(Long id, LeaveRequest leaveRequest);

    boolean deleteLeaveRequest(Long id);

}
