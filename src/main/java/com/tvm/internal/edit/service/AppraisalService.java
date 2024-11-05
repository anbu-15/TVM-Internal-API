package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Appraisal;
import com.tvm.internal.edit.model.SelfAssessment;

import java.util.List;

public interface AppraisalService {
    List<Appraisal> getAllAppraisals();
    Appraisal getAppraisalById(Long id);
    Appraisal createAppraisal(Appraisal appraisal);
    Appraisal updateAppraisal(Long id, Appraisal appraisalDetails);
    void deleteAppraisal(Long id);
    String saveAppraisal(Appraisal appraisal);
    SelfAssessment getSelfAssessmentService(Long id);
}
