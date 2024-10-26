package com.tvm.internal.edit.controller;
import com.tvm.internal.edit.model.Timesheet;
import com.tvm.internal.edit.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @GetMapping("/timesheets/{id}")
    public Timesheet getTimesheetById(@PathVariable UUID id) {
        return timesheetService.getTimesheetById(id);
    }

    @PostMapping("/timesheets")
    public Timesheet createTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetService.createTimesheet(timesheet);
    }

    @PutMapping("/timesheets/{id}")
    public Timesheet updateTimesheet(@PathVariable UUID id, @RequestBody Timesheet timesheet) {
        return timesheetService.updateTimesheet(id, timesheet);
    }

    @DeleteMapping("/timesheets/{id}")
    public void deleteTimesheet(@PathVariable UUID id) {
        timesheetService.deleteTimesheet(id);
    }
}

