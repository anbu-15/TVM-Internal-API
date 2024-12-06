package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hrsType;
    private String description;
    private String location;
    private String project;
    private String subtask;
    private String task;
    private String totalHours;
    private boolean wfol;

    @ElementCollection
    private Map<String, Double> hours = new HashMap<>();

}
