package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.time.LocalDate;

@Entity
@Data
public class Goal {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "GOAL_ID")
        private Long goalId;
        @Column(name="NAME",nullable = false)
        private String name;
        @Column(name="DESCRIPTION",nullable = false)
        private String description;
        @Column(name="PRIORITY",nullable = false)
        private String priority;
        @Column(name="START_DATE",nullable = false)
        private LocalDate startDate;
        @Column(name="END_POINT",nullable = false)
        private LocalDate endDate;
        @Column(name="PROGRESS",nullable = false)
        private Integer progress;

    }


