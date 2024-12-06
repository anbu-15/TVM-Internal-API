package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Education;

import java.util.List;

public interface EducationService {
    Education createEducation(Education education);

    List<Education> getAllEducations();

    Education getEducationById(Long id);

    Education updateEducation(Long id, Education education);

    void deleteEducation(Long id);

    boolean existsById(Long id);
}