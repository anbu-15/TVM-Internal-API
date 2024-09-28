package com.tvm.internal.edit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Tasks {

    @Id
    @Column(name = "TASK_ID")
    private Integer taskId;
    @Column(name = "TASK_OWNER")
    private String taskOwner;
    @Column(name = "TASK_NAME")
    private String taskName;
    @Column(name = "DESCRTPTION")
    private String description;
    @Column(name = "PRIORITY")
    private Integer priority;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "DUE_DATE")
    private Date dueDate;
    @Column(name = "REMINDER")
    private Date reminder;

}
