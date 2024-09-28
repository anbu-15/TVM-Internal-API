package com.tvm.internal.edit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvm.internal.edit.model.LeaveReport;
import com.tvm.internal.edit.service.LeaveReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/leave-reports")
public class LeaveReportController {

    @Autowired
    LeaveReportService leaveReportService;

//    @PostMapping
//    public ResponseEntity<LeaveReport> createLeaveReport(@RequestBody LeaveReport leaveReport) {
//        LeaveReport createdLeaveReport = leaveReportService.saveLeaveReport(leaveReport);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdLeaveReport);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveReport> getLeaveReportById(@PathVariable Long id) {
        return leaveReportService.findLeaveReportById(id)
                .map(report -> ResponseEntity.ok(report))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/leave")
    public ResponseEntity<LeaveReport> createLeaveReport(
            @RequestParam(value = "file") MultipartFile image,
            @RequestParam(value = "leaveReport") String leaveReportJson) {
        try {
            // Convert JSON string to LeaveReport object
            ObjectMapper objectMapper = new ObjectMapper();
            LeaveReport leaveReport = objectMapper.readValue(leaveReportJson, LeaveReport.class);

            // Process image
            if (image != null && !image.isEmpty()) {
                byte[] imageBytes = image.getBytes();
                leaveReport.setProfilePicture(imageBytes);
            }

            LeaveReport createdLeaveReport = leaveReportService.saveLeaveReport(leaveReport);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLeaveReport);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





    @GetMapping
    public ResponseEntity<List<LeaveReport>> getAllLeaveReports() {
        List<LeaveReport> leaveReports = leaveReportService.findAllLeaveReports();
        return ResponseEntity.ok(leaveReports);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveReport(@PathVariable Long id) {
        leaveReportService.deleteLeaveReport(id);
        return ResponseEntity.noContent().build();
    }
}
