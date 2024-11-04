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

    private String finalPerformanceRating;
    private String managerSignature;
    private String employeeSignature;
    private String date;

    @Data
    @Embeddable
    public static class PerformanceGoal {
        private String goal;
        private String goalType;
        private String completionStatus;
        private String comments;
    }

    @Data
    @Embeddable
    public static class SelfAssessment {
        private String strengths;
        private String accomplishments;
        private String challenges;
        private String areasForImprovement;
        private String overallPerformance;
    }

    @Data
    @Embeddable
    public static class ManagerAssessment {
        private String jobKnowledge;
        private String qualityOfWork;
        private String productivity;
        private String communicationSkills;
        private String problemSolvingSkills;
        private String teamwork;
        private String initiative;
        private String overallRating;
    }

    @Data
    @Embeddable
    public static class Strengths {
        private String keyStrengths;
        private String examples;
    }

    @Data
    @Embeddable
    public static class AreasForImprovement {
        private String keyAreasForImprovement;
        private String trainingDevelopmentNeeds;
    }

    @Data
    @Embeddable
    public static class Feedback {
        private String managerFeedback;
        private String peerTeamFeedback;
        private String employeeFeedbackOnProcess;
    }

    @Data
    @Embeddable
    public static class ActionPlanAndGoal {
        private String goal;
        private String timeline;
        private String resourcesNeeded;
    }
}
