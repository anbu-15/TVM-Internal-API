package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Timesheet;

import java.util.List;

public interface TimesheetService {

    List<Timesheet> getAllTimesheets();

    Timesheet getTimesheetById(Long id);

    Timesheet createTimesheet(Timesheet timesheet);

    Timesheet updateTimesheet(Long id, Timesheet timesheetDetails);

    void deleteTimesheet(Long id);

}
