package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LEAVE_TYPE", nullable = false)
    private String leaveType;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDate endDate;

    @Column(name = "TOTAL_DAYS", nullable = false)
    private int totalDays;

    @Column(name = "REASON_FOR_LEAVE", nullable = false)
    private String reasonforLeave;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "DATE_OF_REQUEST", nullable = false)
    private LocalDate dateOfRequest;

    @Column(name = "BOOKED", nullable = false)
    private int booked;

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    @Column(name = "REASON_FOR_REJECTED", nullable = false)
    private String reasonforRejected;

    @Column(name = "COLOR", nullable = false)
    private String color;

}
