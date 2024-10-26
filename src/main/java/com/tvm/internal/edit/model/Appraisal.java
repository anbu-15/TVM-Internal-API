package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Appraisal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID appraisalId;

    private UUID employeeId;
    private String reviewPeriod;
    private String reviewDate;
    private int performanceScore;
    private String feedback;

    @ElementCollection
    private List<String> goals;
}
