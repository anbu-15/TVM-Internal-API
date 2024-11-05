package com.tvm.internal.edit.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ActionPlanAndGoal {
        private String goal;
        private String timeline;
        private String resourcesNeeded;
    }