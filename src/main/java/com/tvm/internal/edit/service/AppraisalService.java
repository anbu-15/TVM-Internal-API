package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Appraisal;

import java.util.List;
import java.util.UUID;

public interface AppraisalService {
    List<Appraisal> getAllAppraisals();
    Appraisal getAppraisalById(UUID id);
    Appraisal createAppraisal(Appraisal appraisal);
    Appraisal updateAppraisal(UUID id, Appraisal appraisalDetails);
    void deleteAppraisal(UUID id);
}
