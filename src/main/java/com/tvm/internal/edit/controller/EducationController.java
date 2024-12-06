package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Education;
import com.tvm.internal.edit.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Education> getEducationById(@PathVariable Long id) {
        Optional<Education> education = Optional.ofNullable(educationService.getEducationById(id));
        if (education.isPresent()) {
            return ResponseEntity.ok(education.get()); // 200 OK if found
        } else {
            return ResponseEntity.noContent().header("X-Message", "Education record not found with ID: " + id).build(); // 204 No Content with message in header
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEducation(
            @PathVariable Long id, @RequestBody Education education) {
        if (!educationService.existsById(id)) {
            return ResponseEntity.noContent().header("X-Message", "No record found with ID: " + id).build(); // 204 No Content with message in header
        }

        Education updatedEducation = educationService.updateEducation(id, education);
        return ResponseEntity.ok("Education updated successfully"); // 200 OK with success message
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable Long id) {
        if (!educationService.existsById(id)) {
            return ResponseEntity.noContent().header("X-Message", "No record found with ID: " + id).build(); // 204 No Content with message in header
        }

        educationService.deleteEducation(id);
        return ResponseEntity.noContent().header("X-Message", "Education record deleted successfully").build(); // 204 No Content with success message
    }
}
