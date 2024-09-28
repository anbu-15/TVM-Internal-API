package com.tvm.internal.edit.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LeaveReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, unique = true)
    private Long employeeId;  // Required, unique employee ID

    @Column(name = "employee_name", nullable = false)
    private String employeeName; // Required, name of the employee

    @Column(name = "profile_picture")
    @Lob
    private byte[] profilePicture; // Optional, path to the employee's profile picture

    @Column(name = "leave_booked")
    private Integer leaveBooked; // Optional, number of leave days booked

    @Column(name = "general_timing")
    private String generalTiming; // Optional, general working hours of the employee

    @Column(name = "status", nullable = false)
    private String status; // Required, e.g., "active", "on leave"

}

