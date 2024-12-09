package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Education;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EducationService {
    Education createEducation(Education education);

    List<Education> getAllEducations();

    Education getEducationById(Long id);

    Education updateEducation(Long id, Education education);

    ResponseEntity<String> deleteEducation(Long id);

    boolean existsById(Long id);
}