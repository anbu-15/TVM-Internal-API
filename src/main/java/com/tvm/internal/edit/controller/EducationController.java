package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Education;
import com.tvm.internal.edit.service.EducationService;
import com.tvm.internal.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {
    @Autowired
    private EducationService educationService;

    @PostMapping
    public ResponseEntity<Education> createEducation(@RequestBody Education education) {
        return ResponseEntity.ok(educationService.createEducation(education));
    }

    @GetMapping
    public ResponseEntity<List<Education>> getAllEducations() {
        return ResponseEntity.ok(educationService.getAllEducations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEducationById(@PathVariable Long id) {
        try {
            Education education = educationService.getEducationById(id); // This will throw exception if not found
            return ResponseEntity.ok(education); // 200 OK if found
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("X-Message", ex.getMessage()).body(ex.getMessage()); // 404 Not Found with error message
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEducation(@PathVariable Long id, @RequestBody Education education) {
        Education updatedEducation = educationService.updateEducation(id, education);
        return ResponseEntity.ok().header("X-Message", "Education updated successfully").body(updatedEducation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
        return ResponseEntity.ok("Education record with ID " + id + " deleted successfully");
    }

}