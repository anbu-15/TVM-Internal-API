package com.tvm.internal.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvm.internal.edit.controller.LeaveReportController;
import com.tvm.internal.edit.model.LeaveReport;
import com.tvm.internal.edit.service.LeaveReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LeaveReportControllerTest {

    @Mock
    private LeaveReportService leaveReportService;

    @InjectMocks
    private LeaveReportController leaveReportController;

    private LeaveReport leaveReport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        leaveReport = new LeaveReport();
        leaveReport.setEmployeeName("John Doe");
        leaveReport.setProfilePicture("image data".getBytes());
        leaveReport.setLeaveBooked(5);
        leaveReport.setGeneralTiming("9 AM - 5 PM");
        leaveReport.setStatus("active");
    }

    @Test
    void testGetLeaveReportById_Success() {
        when(leaveReportService.findLeaveReportById(1L)).thenReturn(Optional.of(leaveReport));

        ResponseEntity<LeaveReport> response = leaveReportController.getLeaveReportById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getEmployeeName()).isEqualTo("John Doe");
        verify(leaveReportService, times(1)).findLeaveReportById(1L);
    }

    @Test
    void testGetLeaveReportById_NotFound() {
        when(leaveReportService.findLeaveReportById(1L)).thenReturn(Optional.empty());

        ResponseEntity<LeaveReport> response = leaveReportController.getLeaveReportById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(leaveReportService, times(1)).findLeaveReportById(1L);
    }

    @Test
    void testCreateLeaveReport_Success() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "image data".getBytes());
        String leaveReportJson = new ObjectMapper().writeValueAsString(leaveReport);
        when(leaveReportService.saveLeaveReport(any(LeaveReport.class))).thenReturn(leaveReport);

        ResponseEntity<LeaveReport> response = leaveReportController.createLeaveReport(file, leaveReportJson);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getEmployeeName()).isEqualTo("John Doe");
        verify(leaveReportService, times(1)).saveLeaveReport(any(LeaveReport.class));
    }

    @Test
    void testCreateLeaveReport_InternalServerError() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "image data".getBytes());
        String leaveReportJson = "invalid json";

        ResponseEntity<LeaveReport> response = leaveReportController.createLeaveReport(file, leaveReportJson);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        verify(leaveReportService, never()).saveLeaveReport(any(LeaveReport.class));
    }

    @Test
    void testGetAllLeaveReports() {
        when(leaveReportService.findAllLeaveReports()).thenReturn(Arrays.asList(leaveReport));

        ResponseEntity<List<LeaveReport>> response = leaveReportController.getAllLeaveReports();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().get(0).getEmployeeName()).isEqualTo("John Doe");
        verify(leaveReportService, times(1)).findAllLeaveReports();
    }

    @Test
    void testDeleteLeaveReport() {
        doNothing().when(leaveReportService).deleteLeaveReport(1L);

        ResponseEntity<Void> response = leaveReportController.deleteLeaveReport(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(leaveReportService, times(1)).deleteLeaveReport(1L);
    }
}
