package com.tvm.internal.Service;

import com.tvm.internal.edit.model.LeaveRequest;
import com.tvm.internal.edit.repo.LeaveRequestRepo;
import com.tvm.internal.edit.serviceImpl.LeaveRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LeaveRequestServiceImplTest {

    @Mock
    private LeaveRequestRepo leaveRequestRepo;

    @InjectMocks
    private LeaveRequestServiceImpl leaveRequestService;

    private LeaveRequest leaveRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        leaveRequest = new LeaveRequest();
        leaveRequest.setId(1L);
        leaveRequest.setLeaveType("Vacation");
//        leaveRequest.setStartDate(LocalDate.now());
//        leaveRequest.setEndDate(LocalDate.now().plusDays(5));
        leaveRequest.setTotalDays(5);
        leaveRequest.setStatus("Pending");
//        leaveRequest.setReasonforLeave("Family trip");
        leaveRequest.setDateOfRequest(LocalDate.now());
//        leaveRequest.setComment("Looking forward to it");
//        leaveRequest.setReasonforRejected(null);
        leaveRequest.setColor("green");
    }

    @Test
    void getAllLeaveRequests() {
        when(leaveRequestRepo.findAll()).thenReturn(Arrays.asList(leaveRequest));

        List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests();
        assertEquals(1, leaveRequests.size());
        assertEquals("Vacation", leaveRequests.get(0).getLeaveType());
        verify(leaveRequestRepo, times(1)).findAll();
    }

    @Test
    void getLeaveRequestById_existingId() {
        when(leaveRequestRepo.findById(1L)).thenReturn(Optional.of(leaveRequest));

        Optional<LeaveRequest> foundLeaveRequest = leaveRequestService.getLeaveRequestById(1L);
        assertTrue(foundLeaveRequest.isPresent());
        assertEquals("Vacation", foundLeaveRequest.get().getLeaveType());
        verify(leaveRequestRepo, times(1)).findById(1L);
    }

    @Test
    void getLeaveRequestById_nonExistingId() {
        when(leaveRequestRepo.findById(2L)).thenReturn(Optional.empty());

        Optional<LeaveRequest> foundLeaveRequest = leaveRequestService.getLeaveRequestById(2L);
        assertFalse(foundLeaveRequest.isPresent());
        verify(leaveRequestRepo, times(1)).findById(2L);
    }

    @Test
    void createLeaveRequest() {
        when(leaveRequestRepo.save(leaveRequest)).thenReturn(leaveRequest);

        LeaveRequest createdLeaveRequest = leaveRequestService.createLeaveRequest(leaveRequest);
        assertNotNull(createdLeaveRequest);
        assertEquals("Vacation", createdLeaveRequest.getLeaveType());
        verify(leaveRequestRepo, times(1)).save(leaveRequest);
    }

    @Test
    void updateLeaveRequest_existingId() {
        when(leaveRequestRepo.findById(1L)).thenReturn(Optional.of(leaveRequest));
        when(leaveRequestRepo.save(any(LeaveRequest.class))).thenReturn(leaveRequest);

        LeaveRequest updatedLeaveRequest = new LeaveRequest();
        updatedLeaveRequest.setLeaveType("Sick Leave");
//        updatedLeaveRequest.setStartDate(LocalDate.now());
//        updatedLeaveRequest.setEndDate(LocalDate.now().plusDays(3));
        updatedLeaveRequest.setTotalDays(3);
        updatedLeaveRequest.setStatus("Approved");
//        updatedLeaveRequest.setReasonforLeave("Flu");
        updatedLeaveRequest.setDateOfRequest(LocalDate.now());
//        updatedLeaveRequest.setComment("Need to recover");
//        updatedLeaveRequest.setReasonforRejected(null);
        updatedLeaveRequest.setColor("yellow");

        Optional<LeaveRequest> result = leaveRequestService.updateLeaveRequest(1L, updatedLeaveRequest);
        assertNotNull(result);
        assertEquals("Sick Leave", result.get());
        verify(leaveRequestRepo, times(1)).findById(1L);
        verify(leaveRequestRepo, times(1)).save(any(LeaveRequest.class));
    }

    @Test
    void updateLeaveRequest_nonExistingId() {
        when(leaveRequestRepo.findById(2L)).thenReturn(Optional.empty());

        Optional<LeaveRequest> result = leaveRequestService.updateLeaveRequest(2L, leaveRequest);
        assertNull(result);
        verify(leaveRequestRepo, times(1)).findById(2L);
    }

    @Test
    void deleteLeaveRequest() {
        doNothing().when(leaveRequestRepo).deleteById(1L);

        leaveRequestService.deleteLeaveRequest(1L);
        verify(leaveRequestRepo, times(1)).deleteById(1L);
    }
}
