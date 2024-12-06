package com.tvm.internal.edit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String project;
    private String location;
    private String hoursType;
    private String task;
    private String subtask;
    private String dailyHours;
    private int totalHours;
    private String description;
}
