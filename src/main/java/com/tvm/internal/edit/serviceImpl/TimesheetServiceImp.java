package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Timesheet;
import com.tvm.internal.edit.repo.TimesheetRepository;
import com.tvm.internal.edit.service.TimesheetService;

import com.tvm.internal.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TimesheetServiceImp implements TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }

    public Timesheet getTimesheetById(Long id) {
        return timesheetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Timesheet not found"));
    }

    public Timesheet createTimesheet(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    public Timesheet updateTimesheet(Long id, Timesheet timesheetDetails) {
        Timesheet timesheet = getTimesheetById(id);
        timesheet.setHrsType(timesheetDetails.getHrsType());
        timesheet.setDescription(timesheetDetails.getDescription());
        timesheet.setLocation(timesheetDetails.getLocation());
        timesheet.setProject(timesheetDetails.getProject());
        timesheet.setSubtask(timesheetDetails.getSubtask());
        timesheet.setTask(timesheetDetails.getTask());
        timesheet.setTotalHours(timesheetDetails.getTotalHours());
        timesheet.setWfol(timesheetDetails.isWfol());
        timesheet.setHours(timesheetDetails.getHours());

        return timesheetRepository.save(timesheet);
    }

    public void deleteTimesheet(Long id) {
        timesheetRepository.deleteById(id);
    }
}

