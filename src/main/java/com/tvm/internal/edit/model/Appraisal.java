package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "appraisals")
public class Appraisal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;
    private String name;
    private String jobTitle;
    private String department;
    private String managerName;
    private String appraisalPeriod;
    private String finalPerformanceRating;
    private String managerSignature;
    private String employeeSignature;
    private String date;

    @ElementCollection
    private List<PerformanceGoal> performanceGoals;

    @Embedded
    private SelfAssessment selfAssessment;

    @Embedded
    private ManagerAssessment managerAssessment;

    @Embedded
    private Strengths strengths;

    @Embedded
    private AreasForImprovement areasForImprovement;

    @Embedded
    private Feedback feedback;

    @ElementCollection
    private List<ActionPlanAndGoal> actionPlanAndGoals;
}
