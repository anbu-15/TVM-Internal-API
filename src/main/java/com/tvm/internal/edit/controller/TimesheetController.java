package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Timesheet;
import com.tvm.internal.edit.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/timesheet")
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @PostMapping
    public ResponseEntity<Timesheet> createTimesheet(@RequestBody Timesheet timesheet) {
        Timesheet createdTimesheet = timesheetService.createTimeSheet(timesheet);
        return ResponseEntity.ok(createdTimesheet);
    }

    @GetMapping
    public ResponseEntity<List<Timesheet>> getAllTimeSheets() {
        List<Timesheet> timesheets = timesheetService.getAllTimeSheets();
        return ResponseEntity.ok(timesheets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {
        try {
            Timesheet timesheet = timesheetService.getTimeSheetById(id);
            return ResponseEntity.ok(timesheet);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTimesheet(@PathVariable Long id, @RequestBody Timesheet timesheet) {
        try {
            Timesheet updatedTimesheet = timesheetService.updateTimeSheet(id, timesheet);
            return ResponseEntity.ok(updatedTimesheet);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    }

