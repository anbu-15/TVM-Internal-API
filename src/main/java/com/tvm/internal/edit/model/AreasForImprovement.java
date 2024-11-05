package com.tvm.internal.edit.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class AreasForImprovement {
        private String keyAreasForImprovement;
        private String trainingDevelopmentNeeds;
}