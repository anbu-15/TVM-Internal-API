package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Timesheet;

import java.util.List;
import java.util.UUID;

public interface TimesheetService {
    List<Timesheet> getAllTimesheets();
    Timesheet getTimesheetById(UUID id);
    Timesheet createTimesheet(Timesheet timesheet);
    Timesheet updateTimesheet(UUID id, Timesheet timesheetDetails);
    void deleteTimesheet(UUID id);
}
