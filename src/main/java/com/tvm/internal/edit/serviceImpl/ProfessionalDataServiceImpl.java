package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.ProfessionalData;
import com.tvm.internal.edit.repo.ProfessionalDataRepository;
import com.tvm.internal.edit.service.ProfessionalDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalDataServiceImpl implements ProfessionalDataService {

    private static final Logger logger = LoggerFactory.getLogger(ProfessionalDataServiceImpl.class);

    @Autowired
    private ProfessionalDataRepository professionalDataRepository;

    @Override
    public ProfessionalData createProfessionalData(ProfessionalData professionalData) {
        ProfessionalData createdData = professionalDataRepository.save(professionalData);
        logger.info("Successfully created professional data: {}", createdData);
        return createdData;
    }

    @Override
    public List<ProfessionalData> getAllProfessionalData() {
        List<ProfessionalData> dataList = professionalDataRepository.findAll();
        if (dataList.isEmpty()) {
            logger.warn("No ProfessionalData records found.");
        } else {
            logger.info("Successfully retrieved {} ProfessionalData records.", dataList.size());
        }
        return dataList;
    }

    @Override
    public ProfessionalData getProfessionalDataById(Long id) {
        ProfessionalData professionalData = professionalDataRepository.findById(id).orElse(null);
        if (professionalData == null) {
            logger.warn("ProfessionalData not found with id: {}", id);
        } else {
            logger.info("Successfully retrieved ProfessionalData with id: {}", id);
        }
        return professionalData;
    }



    @Override
    public ProfessionalData updateProfessionalData(Long id, ProfessionalData professionalData) {
        ProfessionalData existingData = professionalDataRepository.findById(id).orElse(null);

        if (existingData == null) {
            logger.warn("ProfessionalData not found with id: " + id);
            return null;
        }

        logger.info("Successfully updated professional data record with id: " + id);
        existingData.setCompanyName(professionalData.getCompanyName());
        existingData.setDurationFrom(professionalData.getDurationFrom());
        existingData.setDurationTo(professionalData.getDurationTo());
        existingData.setEmpPreference(professionalData.getEmpPreference());
        existingData.setRole(professionalData.getRole());
        existingData.setType(professionalData.getType());
        existingData.setAchievements(professionalData.getAchievements());
        existingData.setPhysical(professionalData.getPhysical());
        existingData.setArrest(professionalData.getArrest());
        existingData.setEmployment(professionalData.getEmployment());
        existingData.setSurgical(professionalData.getSurgical());

        return professionalDataRepository.save(existingData);
    }

    @Override
    public ResponseEntity<String> deleteProfessionalData(Long id) {
        ProfessionalData existingData = professionalDataRepository.findById(id).orElse(null);
        if (existingData == null) {
            logger.warn("ProfessionalData not found with id: {}", id); // Log when not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProfessionalData not found with id: " + id);
        }
        logger.info("Attempting to delete ProfessionalData with id: {}", id);
        professionalDataRepository.deleteById(id);
        logger.info("Successfully deleted ProfessionalData with id: {}", id);

        return ResponseEntity.ok("Successfully deleted");
    }


    @Override
    public boolean existsById(Long id) {
        return professionalDataRepository.existsById(id);
    }
}
