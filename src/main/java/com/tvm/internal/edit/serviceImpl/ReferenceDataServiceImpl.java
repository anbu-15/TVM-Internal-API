package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.ReferenceData;
import com.tvm.internal.edit.repo.ReferenceDataRepository;
import com.tvm.internal.edit.service.ReferenceDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReferenceDataServiceImpl implements ReferenceDataService {
    private static final Logger logger = LoggerFactory.getLogger(ReferenceDataServiceImpl.class);

    @Autowired
    private ReferenceDataRepository repository;

    @Override
    public ReferenceData createReferenceData(ReferenceData referenceData) {
        logger.info("Successfully created ReferenceData with id: {}", referenceData.getFullName());
        return repository.save(referenceData);
    }

    @Override
    public List<ReferenceData> getAllReferenceData() {
        logger.info("Successfully retrieved all ReferenceData records");
        return repository.findAll();
    }

    @Override
    public ReferenceData getReferenceDataById(Long id) {
        Optional<ReferenceData> optionalData = repository.findById(id);

        if (optionalData.isPresent()) {
            logger.info("Successfully retrieved ReferenceData with id: {}", id);
            return optionalData.get();
        } else {
            logger.error("ReferenceData not found with id: {}", id);
            return null;
        }
    }

    @Override
    public ReferenceData updateReferenceData(Long id, ReferenceData updatedData) {
        Optional<ReferenceData> existingDataOptional = repository.findById(id);
        if (existingDataOptional.isPresent()) {
            ReferenceData existingData = existingDataOptional.get();
            existingData.setFullName(updatedData.getFullName());
            existingData.setRelationship(updatedData.getRelationship());
            existingData.setContactNo(updatedData.getContactNo());
            existingData.setBusiness(updatedData.getBusiness());
            logger.info("Successfully updated ReferenceData with id: {}", id);
            return repository.save(existingData);
        } else {
            logger.error("ReferenceData not found with id: {}", id);
            return null;
        }
    }

    @Override
    public ResponseEntity<String> deleteReferenceData(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            logger.info("Successfully deleted ReferenceData with id: "+ id);
            return ResponseEntity.status(HttpStatus.OK).body("ReferenceData deleted successfully");
        } else {
            logger.error("ReferenceData not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ReferenceData not found with id: " + id);
        }
    }


    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
