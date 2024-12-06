package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Timesheet;
import com.tvm.internal.edit.repo.TimesheetRepository;
import com.tvm.internal.edit.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TimesheetServiceImp implements TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepo;

    @Override
    public Timesheet createTimeSheet(Timesheet timesheet) {
        return timesheetRepo.save(timesheet);
    }

    @Override
    public List<Timesheet> getAllTimeSheets() {
        return timesheetRepo.findAll();
    }

    @Override
    public Timesheet getTimeSheetById(Long id) {
        return timesheetRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Timesheet with ID " + id + " not found."));
    }

    public Timesheet updateTimeSheet(Long id, Timesheet timesheet) {
        Timesheet existingTimesheet = timesheetRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Timesheet with ID " + id + " not found."));
        timesheet.setId(existingTimesheet.getId());
        return timesheetRepo.save(timesheet);
    }
}

