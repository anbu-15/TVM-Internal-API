package com.tvm.internal.edit.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ManagerAssessment {
        private String jobKnowledge;
        private String qualityOfWork;
        private String productivity;
        private String communicationSkills;
        private String problemSolvingSkills;
        private String teamwork;
        private String initiative;
        private String overallRating;
}