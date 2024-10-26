package com.tvm.internal.edit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID timesheetId;

    private UUID employeeId;
    private String date;
    private int hoursWorked;
    private UUID projectId;
    private String description;

}
