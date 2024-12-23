package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Education;
import com.tvm.internal.edit.repo.EducationRepository;
import com.tvm.internal.edit.service.EducationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {
    private static final Logger logger = LoggerFactory.getLogger(EducationController.class);

    @Autowired
    private EducationService educationService;

    @Autowired
    private EducationRepository educationRepository;

    @PostMapping
    public ResponseEntity<List<Education>> createEducations(@RequestBody List<Education> educations) {
        List<Education> savedEducations = educationService.createEducations(educations);
        return ResponseEntity.ok(savedEducations);
    }

    @GetMapping
    public ResponseEntity<List<Education>> getAllEducations() {
        return ResponseEntity.ok(educationService.getAllEducations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEducationById(@PathVariable Long id) {
        if (!educationService.existsById(id)) {
            logger.warn("Education not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("X-Message", "Education not found with id: " + id).body("Education not found with id: " + id);
        }
        Education education = educationService.getEducationById(id);
        logger.info("Successfully retrieved education with id: {}", id);
        return ResponseEntity.ok(education);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEducation(@PathVariable Long id, @RequestBody Education education) {
        Education updatedEducation = educationService.updateEducation(id, education);
        if (updatedEducation == null) {
            logger.info("Education not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Education not found with id: " + id);
        }
        logger.info("Successfully updated education record with id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEducation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable Long id) {
        ResponseEntity<String> deleteEducation = educationService.deleteEducation(id);

        if (deleteEducation.getStatusCode() == HttpStatus.NOT_FOUND) {
            logger.info("Education not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Education not found with id: " + id);
        }

        logger.info("Successfully deleted education record with id: {}", id);
        return deleteEducation;
    }


}