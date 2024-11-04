package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Appraisal;
import com.tvm.internal.edit.repo.AppraisalRepository;
import com.tvm.internal.edit.service.AppraisalService;
import com.tvm.internal.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppraisalServiceImp implements AppraisalService {
    @Autowired
    private AppraisalRepository appraisalRepository;

    public List<Appraisal> getAllAppraisals() {
        return appraisalRepository.findAll();
    }

    public Appraisal getAppraisalById(Long id) {
        return appraisalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appraisal not found"));
    }

    public Appraisal createAppraisal(Appraisal appraisal) {
        return appraisalRepository.save(appraisal);
    }


    public Appraisal updateAppraisal(Long id, Appraisal appraisalDetails) {
        Appraisal appraisal = getAppraisalById(id);
        // Update fields
        appraisal.setEmployeeId(appraisalDetails.getEmployeeId());
        appraisal.setName(appraisalDetails.getName());
        appraisal.setJobTitle(appraisalDetails.getJobTitle());
        appraisal.setDepartment(appraisalDetails.getDepartment());
        appraisal.setManagerName(appraisalDetails.getManagerName());
        appraisal.setAppraisalPeriod(appraisalDetails.getAppraisalPeriod());
        appraisal.setPerformanceGoals(appraisalDetails.getPerformanceGoals());
        appraisal.setSelfAssessment(appraisalDetails.getSelfAssessment());
        appraisal.setManagerAssessment(appraisalDetails.getManagerAssessment());
        appraisal.setStrengths(appraisalDetails.getStrengths());
        appraisal.setAreasForImprovement(appraisalDetails.getAreasForImprovement());
        appraisal.setFeedback(appraisalDetails.getFeedback());
        appraisal.setActionPlanAndGoals(appraisalDetails.getActionPlanAndGoals());
        appraisal.setFinalPerformanceRating(appraisalDetails.getFinalPerformanceRating());
        appraisal.setManagerSignature(appraisalDetails.getManagerSignature());
        appraisal.setEmployeeSignature(appraisalDetails.getEmployeeSignature());
        appraisal.setDate(appraisalDetails.getDate());

        return appraisalRepository.save(appraisal);
    }


    public void deleteAppraisal(Long id) {
        appraisalRepository.deleteById(id);
    }

    public String saveAppraisal(Appraisal appraisal) {
        appraisalRepository.save(appraisal);
        return "testing";
    }

}
