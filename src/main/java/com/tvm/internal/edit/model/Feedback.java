package com.tvm.internal.edit.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Feedback {
        private String managerFeedback;
        private String peerTeamFeedback;
        private String employeeFeedbackOnProcess;
}