package com.tvm.internal.edit.service;


import com.tvm.internal.edit.model.LeaveReport;

import java.util.List;
import java.util.Optional;

public interface LeaveReportService {
    LeaveReport saveLeaveReport(LeaveReport leaveReport);
    Optional<LeaveReport> findLeaveReportById(Long id);
    List<LeaveReport> findAllLeaveReports() ;
    void deleteLeaveReport(Long id) ;
}

