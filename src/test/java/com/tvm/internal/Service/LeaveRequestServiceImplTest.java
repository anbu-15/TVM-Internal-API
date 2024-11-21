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
        leaveRequest.setTotalDays(5);
        leaveRequest.setStatus("Pending");
        leaveRequest.setReason("Family trip");
        leaveRequest.setDateOfRequest(LocalDate.now());
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
        updatedLeaveRequest.setLeaveType("Vacation");
        updatedLeaveRequest.setTotalDays(5);
        updatedLeaveRequest.setStatus("Approved");
        updatedLeaveRequest.setReason("Family trip");
        updatedLeaveRequest.setDateOfRequest(LocalDate.now());
        updatedLeaveRequest.setColor("yellow");
        Optional<LeaveRequest> result = leaveRequestService.updateLeaveRequest(1L, updatedLeaveRequest);
        assertNotNull(result);
        assertEquals("Vacation", updatedLeaveRequest.getLeaveType());
        verify(leaveRequestRepo, times(1)).findById(1L);
        verify(leaveRequestRepo, times(1)).save(any(LeaveRequest.class));
    }

    @Test
    void updateLeaveRequest_nonExistingId() {
        when(leaveRequestRepo.findById(2L)).thenReturn(Optional.empty());
        Optional<LeaveRequest> result = leaveRequestService.updateLeaveRequest(2L, leaveRequest);
        assertEquals(Optional.empty(), result);
        verify(leaveRequestRepo, times(1)).findById(2L);
    }

    @Test
    public void testDeleteLeaveRequest_Success() {
        Long leaveRequestId = 1L;
        when(leaveRequestRepo.existsById(leaveRequestId)).thenReturn(true);
        boolean result = leaveRequestService.deleteLeaveRequest(leaveRequestId);
        assertTrue(result);
        verify(leaveRequestRepo, times(1)).deleteById(leaveRequestId);
    }

    @Test
    public void testDeleteLeaveRequest_Failure() {
        Long leaveRequestId = 1L;
        when(leaveRequestRepo.existsById(leaveRequestId)).thenReturn(false);
        boolean result = leaveRequestService.deleteLeaveRequest(leaveRequestId);
        assertFalse(result);
        verify(leaveRequestRepo, times(0)).deleteById(leaveRequestId);
    }
}
