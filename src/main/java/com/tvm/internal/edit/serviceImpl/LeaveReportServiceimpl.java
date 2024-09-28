package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.LeaveReport;
import com.tvm.internal.edit.repo.LeaveReportRepository;
import com.tvm.internal.edit.service.LeaveReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveReportServiceimpl implements LeaveReportService{

    @Autowired
    LeaveReportRepository leaveReportRepository;

    // Create or update a leave report
    public LeaveReport saveLeaveReport(LeaveReport leaveReport) {
        return leaveReportRepository.save(leaveReport);
    }

    // Find a leave report by ID
    public Optional<LeaveReport> findLeaveReportById(Long id) {
        return leaveReportRepository.findById(id);
    }

    // Get all leave reports
    public List<LeaveReport> findAllLeaveReports() {
        return leaveReportRepository.findAll();
    }

    // Delete a leave report by ID
    public void deleteLeaveReport(Long id) {
        leaveReportRepository.deleteById(id);
    }
}
