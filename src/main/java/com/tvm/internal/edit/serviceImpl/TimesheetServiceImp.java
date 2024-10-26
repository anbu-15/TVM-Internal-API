package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Timesheet;
import com.tvm.internal.edit.repo.TimesheetRepository;
import com.tvm.internal.edit.service.TimesheetService;

import com.tvm.internal.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class TimesheetServiceImp implements TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }

    public Timesheet getTimesheetById(UUID id) {
        return timesheetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Timesheet not found"));
    }

    public Timesheet createTimesheet(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    public Timesheet updateTimesheet(UUID id, Timesheet timesheetDetails) {
        Timesheet timesheet = getTimesheetById(id);
        timesheet.setDate(timesheetDetails.getDate());
        timesheet.setHoursWorked(timesheetDetails.getHoursWorked());
        timesheet.setDescription(timesheetDetails.getDescription());
        timesheet.setProjectId(timesheetDetails.getProjectId());
        return timesheetRepository.save(timesheet);
    }

    public void deleteTimesheet(UUID id) {
        timesheetRepository.deleteById(id);
    }
}

