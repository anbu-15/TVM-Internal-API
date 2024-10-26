package com.tvm.internal.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvm.internal.edit.controller.LeaveRequestController;
import com.tvm.internal.edit.model.LeaveRequest;
import com.tvm.internal.edit.service.LeaveRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LeaveRequestControllerTest {

    @InjectMocks
    private LeaveRequestController leaveRequestController;

    @Mock
    private LeaveRequestService leaveRequestService;

    private LeaveRequest leaveRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        leaveRequest = new LeaveRequest();
        leaveRequest.setId(1L);
        leaveRequest.setLeaveType("Sick Leave");
        leaveRequest.setStartDate(LocalDate.now());
        leaveRequest.setEndDate(LocalDate.now().plusDays(3));
        leaveRequest.setTotalDays(3);
        leaveRequest.setReasonforLeave("Flu");
        leaveRequest.setStatus("Pending");
        leaveRequest.setDateOfRequest(LocalDate.now());
        leaveRequest.setBooked(1);
        leaveRequest.setComment("Need rest");
        leaveRequest.setReasonforRejected("");
        leaveRequest.setColor("red");
    }

    @Test
    void testGetAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        leaveRequests.add(leaveRequest);

        when(leaveRequestService.getAllLeaveRequests()).thenReturn(leaveRequests);

        ResponseEntity<List<LeaveRequest>> response = leaveRequestController.getAllLeaveRequests();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(leaveRequests);
        verify(leaveRequestService, times(1)).getAllLeaveRequests();
    }

    @Test
    void testGetLeaveRequestById() {
        when(leaveRequestService.getLeaveRequestById(1L)).thenReturn(Optional.of(leaveRequest));

        ResponseEntity<LeaveRequest> response = leaveRequestController.getLeaveRequestById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(leaveRequest);
        verify(leaveRequestService, times(1)).getLeaveRequestById(1L);
    }

    @Test
    void testGetLeaveRequestById_NotFound() {
        when(leaveRequestService.getLeaveRequestById(1L)).thenReturn(Optional.empty());

        ResponseEntity<LeaveRequest> response = leaveRequestController.getLeaveRequestById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(leaveRequestService, times(1)).getLeaveRequestById(1L);
    }

    @Test
    void testCreateLeaveRequest() {
        when(leaveRequestService.createLeaveRequest(any(LeaveRequest.class))).thenReturn(leaveRequest);

        ResponseEntity<LeaveRequest> response = leaveRequestController.createLeaveRequest(leaveRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(leaveRequest);
        verify(leaveRequestService, times(1)).createLeaveRequest(any(LeaveRequest.class));
    }

    @Test
    void testUpdateLeaveRequest() {
        when(leaveRequestService.updateLeaveRequest(eq(1L), any(LeaveRequest.class))).thenReturn(leaveRequest);

        ResponseEntity<LeaveRequest> response = leaveRequestController.updateLeaveRequest(1L, leaveRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(leaveRequest);
        verify(leaveRequestService, times(1)).updateLeaveRequest(eq(1L), any(LeaveRequest.class));
    }

    @Test
    void testDeleteLeaveRequest() {
        doNothing().when(leaveRequestService).deleteLeaveRequest(1L);

        ResponseEntity<Void> response = leaveRequestController.deleteLeaveRequest(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(leaveRequestService, times(1)).deleteLeaveRequest(1L);
    }
}
