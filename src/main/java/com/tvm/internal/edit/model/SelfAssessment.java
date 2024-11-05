package com.tvm.internal.edit.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
@Data
@Embeddable
public class SelfAssessment {
        private String strengths;
        private String accomplishments;
        private String challenges;
        private String areasForImprovement;
        private String overallPerformance;
}