package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.ReferenceData;
import com.tvm.internal.edit.service.ReferenceDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/referencedata")
public class ReferenceDataController {
    private static final Logger logger = LoggerFactory.getLogger(ReferenceDataController.class);

    @Autowired
    private ReferenceDataService referenceDataService;

    @PostMapping
    public ResponseEntity<ReferenceData> createReferenceData(@RequestBody ReferenceData referenceData) {
        ReferenceData createdData = referenceDataService.createReferenceData(referenceData);
        logger.info("Successfully created ReferenceData with id: {}", createdData.getId());
        return ResponseEntity.ok(createdData);
    }

    @GetMapping
    public ResponseEntity<List<ReferenceData>> getAllReferenceData() {
        List<ReferenceData> allData = referenceDataService.getAllReferenceData();
        logger.info("Successfully retrieved all ReferenceData records");
        return ResponseEntity.ok(allData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReferenceDataById(@PathVariable Long id) {
        ReferenceData data = referenceDataService.getReferenceDataById(id);
        if (!referenceDataService.existsById(id)) {
            logger.warn("ReferenceData not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("X-Message", "ReferenceData not found with id: " + id)
                    .body("ReferenceData not found with id: " + id);
        }
        logger.info("Successfully retrieved ReferenceData with id: {}", id);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReferenceData(@PathVariable Long id, @RequestBody ReferenceData referenceData) {
        ReferenceData updatedData = referenceDataService.updateReferenceData(id, referenceData);
        if (updatedData == null) {
            logger.warn("ReferenceData not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ReferenceData not found with id: " + id);
        }
        logger.info("Successfully updated ReferenceData with id: {}", id);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReferenceData(@PathVariable Long id) {
        ResponseEntity<String> deleteResponse = referenceDataService.deleteReferenceData(id);
        if (deleteResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            logger.info("ReferenceData not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ReferenceData not found with id: " + id);
        }
        logger.info("Successfully deleted ReferenceData with id: {}", id);
        return deleteResponse;
    }

}
