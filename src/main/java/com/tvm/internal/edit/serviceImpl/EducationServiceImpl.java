package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Education;
import com.tvm.internal.edit.repo.EducationRepository;
import com.tvm.internal.edit.service.EducationService;
import com.tvm.internal.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {
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
        return educationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Education not found with id: " + id));
    }

    @Override
    public Education updateEducation(Long id, Education education) {
        Education existingEducation = educationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Education not found with id: " + id));
        existingEducation.setCoursePursued(education.getCoursePursued());
        existingEducation.setInstitutionName(education.getInstitutionName());
        existingEducation.setDurationFrom(education.getDurationFrom());
        existingEducation.setDurationTo(education.getDurationTo());
        existingEducation.setCgpaObtained(education.getCgpaObtained());
        return educationRepository.save(existingEducation);
    }

    @Override
    public void deleteEducation(Long id) {
        if (!educationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Education not found with id: " + id);
        }
        educationRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return educationRepository.existsById(id);
    }
}