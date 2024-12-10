package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.ProfessionalData;
import com.tvm.internal.edit.service.ProfessionalDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professionaldata")
public class ProfessionalDataController {

    private static final Logger logger = LoggerFactory.getLogger(ProfessionalDataController.class);

    @Autowired
    private ProfessionalDataService professionalDataService;

    @PostMapping
    public ResponseEntity<ProfessionalData> createProfessionalData(@RequestBody ProfessionalData professionalData) {
        ProfessionalData createdData = professionalDataService.createProfessionalData(professionalData);
        logger.info("Successfully created professional data: {}", createdData);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdData);
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalData>> getAllProfessionalData() {
        List<ProfessionalData> professionalDataList = professionalDataService.getAllProfessionalData();
        if (professionalDataList.isEmpty()) {
            logger.warn("No ProfessionalData records found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(professionalDataList);
        }
        logger.info("Successfully retrieved {} ProfessionalData records.", professionalDataList.size());
        return ResponseEntity.ok(professionalDataList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfessionalDataById(@PathVariable Long id) {
        ProfessionalData professionalData = professionalDataService.getProfessionalDataById(id);
        if (professionalData == null) {
            logger.warn("ProfessionalData not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProfessionalData not found with id: " + id);
        }
        logger.info("Successfully retrieved ProfessionalData with id: {}", id);
        return ResponseEntity.ok(professionalData);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProfessionalData(@PathVariable Long id, @RequestBody ProfessionalData professionalData) {
        ProfessionalData updatedData = professionalDataService.updateProfessionalData(id, professionalData);
        if (updatedData == null) {
            logger.info("ProfessionalData not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProfessionalData not found with id: " + id);
        }
        logger.info("Successfully updated professional data record with id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessionalData(@PathVariable Long id) {
        ResponseEntity<String> deleteResponse = professionalDataService.deleteProfessionalData(id);

        if (deleteResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            logger.info("ProfessionalData not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProfessionalData not found with id: " + id);
        }

        logger.info("Successfully deleted professional data record with id: {}", id);
        return deleteResponse;
    }
}