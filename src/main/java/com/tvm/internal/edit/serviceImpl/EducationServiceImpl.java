package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Education;
import com.tvm.internal.edit.repo.EducationRepository;
import com.tvm.internal.edit.service.EducationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    private static final Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);


    @Autowired
    private EducationRepository educationRepository;

    @Override
    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    @Override
    public List<Education> getAllEducations() {
        return educationRepository.findAll();
    }

    @Override
    public Education getEducationById(Long id) {
        logger.info("Education not found with id : " + id);
        return educationRepository.findById(id).orElse(null);
    }

    @Override
    public Education updateEducation(Long id, Education education) {
        Education existingEducation = educationRepository.findById(id).orElse(null);

        if (existingEducation == null) {
            logger.warn("Education not found with id: " + id);
            return null;
        }
        logger.info("Update education record with id: " + id);
        existingEducation.setCoursePursued(education.getCoursePursued());
        existingEducation.setInstitutionName(education.getInstitutionName());
        existingEducation.setDurationFrom(education.getDurationFrom());
        existingEducation.setDurationTo(education.getDurationTo());
        existingEducation.setCgpaObtained(education.getCgpaObtained());
        return educationRepository.save(existingEducation);
    }

    @Override
    public ResponseEntity<String> deleteEducation(Long id) {
        Education existingEducation = educationRepository.findById(id).orElse(null);
        if (existingEducation == null) {
            logger.warn("Education not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Education not found with id: " + id);
        }
        educationRepository.deleteById(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @Override
    public boolean existsById(Long id) {
        return educationRepository.existsById(id);
    }
}