package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private Integer taskId;
    @Column(name = "TASK_OWNER")
    private String taskOwner;
    @Column(name = "TASK_NAME")
    private String taskName;
    @Column(name = "DESCRTPTION")
    private String description;
    @Column(name = "PRIORITY")
    private String priority;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "DUE_DATE")
    private Date dueDate;
    @Column(name = "REMINDER")
    private Date reminder;

}
