package com.tvm.internal.edit.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PerformanceGoal {
    private String goal;
    private String goalType;
    private String completionStatus;
    private String comments;
}