package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Appraisal;
import com.tvm.internal.edit.model.SelfAssessment;
import com.tvm.internal.edit.service.AppraisalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppraisalController {

    @Autowired
    private AppraisalService appraisalService;

    @GetMapping("/appraisals/getAllAppraisals")
    public List<Appraisal> getAllAppraisals() {
        return appraisalService.getAllAppraisals();
    }

    @GetMapping("/appraisals/{id}")
    public Appraisal getAppraisalById(@PathVariable Long id) {
        return appraisalService.getAppraisalById(id);
    }

    @PostMapping("/appraisals")
    public Appraisal createAppraisal(@RequestBody Appraisal appraisal) {
        return appraisalService.createAppraisal(appraisal);
    }

    @PutMapping("/appraisals/{id}")
    public Appraisal updateAppraisal(@PathVariable Long id, @RequestBody Appraisal appraisal) {
        return appraisalService.updateAppraisal(id, appraisal);
    }

    @DeleteMapping("/appraisals/{id}")
    public void deleteAppraisal(@PathVariable Long id) {
        appraisalService.deleteAppraisal(id);
    }

    @GetMapping("/appraisals/{id}/self-asses")
    public SelfAssessment getSESelfAssessment(@PathVariable Long id) {
        return appraisalService.getSelfAssessmentService(id);
    }
}

