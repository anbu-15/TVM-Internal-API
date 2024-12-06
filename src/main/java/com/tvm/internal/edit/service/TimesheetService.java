package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Timesheet;

import java.util.List;

public interface TimesheetService {
    Timesheet createTimeSheet(Timesheet timesheet);
    List<Timesheet> getAllTimeSheets();
    Timesheet getTimeSheetById(Long id);
    Timesheet updateTimeSheet(Long id, Timesheet timeEntry);
}
