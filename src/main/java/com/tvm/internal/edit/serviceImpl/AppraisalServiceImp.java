package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Appraisal;
import com.tvm.internal.edit.repo.AppraisalRepository;
import com.tvm.internal.edit.service.AppraisalService;
import com.tvm.internal.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppraisalServiceImp implements AppraisalService {
    @Autowired
    private AppraisalRepository appraisalRepository;

    public List<Appraisal> getAllAppraisals() {
        return appraisalRepository.findAll();
    }

    public Appraisal getAppraisalById(UUID id) {
        return appraisalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appraisal not found"));
    }

    public Appraisal createAppraisal(Appraisal appraisal) {
        return appraisalRepository.save(appraisal);
    }

    public Appraisal updateAppraisal(UUID id, Appraisal appraisalDetails) {
        Appraisal appraisal = getAppraisalById(id);
        appraisal.setPerformanceScore(appraisalDetails.getPerformanceScore());
        appraisal.setFeedback(appraisalDetails.getFeedback());
        // Update other fields
        return appraisalRepository.save(appraisal);
    }

    public void deleteAppraisal(UUID id) {
        appraisalRepository.deleteById(id);
    }

}
