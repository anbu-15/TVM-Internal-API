package com.tvm.internal.edit.controller;
import com.tvm.internal.edit.model.Timesheet;
import com.tvm.internal.edit.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @GetMapping("/timesheets/{id}")
    public Timesheet getTimesheetById(@PathVariable Long id) {
        return timesheetService.getTimesheetById(id);
    }

    @GetMapping("/timesheets")
    public List<Timesheet> getAllTimesheet() {
        return timesheetService.getAllTimesheets();
    }

    @PostMapping("/timesheets")
    public Timesheet createTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetService.createTimesheet(timesheet);
    }

    @PutMapping("/timesheets/{id}")
    public Timesheet updateTimesheet(@PathVariable Long id, @RequestBody Timesheet timesheet) {
        return timesheetService.updateTimesheet(id, timesheet);
    }

    @DeleteMapping("/timesheets/{id}")
    public void deleteTimesheet(@PathVariable Long id) {
        timesheetService.deleteTimesheet(id);
    }
}

