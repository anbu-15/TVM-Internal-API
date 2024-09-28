package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.LeaveRequest;
import com.tvm.internal.edit.service.LeaveRequestServiceImpl; // Adjust the import as per your implementation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestServiceImpl leaveRequestService; // Ensure this is the correct service interface

    // Get all leave requests
    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests();
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    // Get leave request by ID
    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) {
        Optional<LeaveRequest> leaveRequest = leaveRequestService.getLeaveRequestById(id);
        return leaveRequest.map(request -> new ResponseEntity<>(request, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new leave request
    @PostMapping
    public ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        LeaveRequest createdLeaveRequest = leaveRequestService.createLeaveRequest(leaveRequest);
        return new ResponseEntity<>(createdLeaveRequest, HttpStatus.CREATED);
    }

    // Update an existing leave request
    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequest> updateLeaveRequest(@PathVariable Long id,
                                                           @RequestBody LeaveRequest leaveRequest) {
        LeaveRequest updatedLeaveRequest = leaveRequestService.updateLeaveRequest(id, leaveRequest);
        return new ResponseEntity<>(updatedLeaveRequest, HttpStatus.OK);
    }

    // Delete a leave request
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
