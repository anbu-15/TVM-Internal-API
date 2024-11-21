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

    @Column(name = "LEAVE_TYPE")
    private String leaveType;

//    @Column(name = "START_DATE")
//    private LocalDate startDate;

//    @Column(name = "END_DATE")
//    private LocalDate endDate;

    @Column(name = "START_END_DATE")
    private String leaveDate;

    @Column(name = "TOTAL_DAYS")
    private int totalDays;

    @Column(name = "REASON_FOR_LEAVE")
    private String reason;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DATE_OF_REQUEST")
    private LocalDate dateOfRequest;

    @Column(name = "BOOKED")
    private int booked;

//    @Column(name = "COMMENT")
//    private String comment;

    @Column(name = "teamEmail")
    private String teamEmail;

//    @Column(name = "REASON_FOR_REJECTED")
//    private String reasonforRejected;

    @Column(name = "COLOR")
    private String color;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveTypes;
}
