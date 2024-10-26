package com.tvm.internal.Service;

import com.tvm.internal.edit.model.LeaveReport;
import com.tvm.internal.edit.repo.LeaveReportRepository;
import com.tvm.internal.edit.serviceImpl.LeaveReportServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class LeaveReportServiceimplTest {

    @InjectMocks
    private LeaveReportServiceimpl leaveReportService;

    @Mock
    private LeaveReportRepository leaveReportRepository;

    private LeaveReport leaveReport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        leaveReport = new LeaveReport();
        leaveReport.setEmployeeId(1L);
        leaveReport.setEmployeeName("John Doe");
        leaveReport.setLeaveBooked(5);
        leaveReport.setGeneralTiming("9 AM - 5 PM");
        leaveReport.setStatus("active");
    }

    @Test
    void testSaveLeaveReport() {
        when(leaveReportRepository.save(any(LeaveReport.class))).thenReturn(leaveReport);

        LeaveReport savedLeaveReport = leaveReportService.saveLeaveReport(leaveReport);

        assertThat(savedLeaveReport).isNotNull();
        assertThat(savedLeaveReport.getEmployeeId()).isEqualTo(1L);
        verify(leaveReportRepository, times(1)).save(any(LeaveReport.class));
    }

    @Test
    void testFindLeaveReportById() {
        when(leaveReportRepository.findById(anyLong())).thenReturn(Optional.of(leaveReport));

        Optional<LeaveReport> foundLeaveReport = leaveReportService.findLeaveReportById(1L);

        assertThat(foundLeaveReport).isPresent();
        assertThat(foundLeaveReport.get()).isEqualTo(leaveReport);
        verify(leaveReportRepository, times(1)).findById(1L);
    }

    @Test
    void testFindLeaveReportByIdNotFound() {
        when(leaveReportRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<LeaveReport> foundLeaveReport = leaveReportService.findLeaveReportById(999L);

        assertThat(foundLeaveReport).isNotPresent();
        verify(leaveReportRepository, times(1)).findById(999L);
    }

    @Test
    void testFindAllLeaveReports() {
        List<LeaveReport> leaveReportsList = new ArrayList<>();
        leaveReportsList.add(leaveReport);

        when(leaveReportRepository.findAll()).thenReturn(leaveReportsList);

        List<LeaveReport> retrievedLeaveReports = leaveReportService.findAllLeaveReports();

        assertThat(retrievedLeaveReports).isNotNull();
        assertThat(retrievedLeaveReports).hasSize(1);
        assertThat(retrievedLeaveReports.get(0)).isEqualTo(leaveReport);
        verify(leaveReportRepository, times(1)).findAll();
    }

    @Test
    void testDeleteLeaveReport() {
        doNothing().when(leaveReportRepository).deleteById(anyLong());

        leaveReportService.deleteLeaveReport(1L);

        verify(leaveReportRepository, times(1)).deleteById(1L);
    }
}
