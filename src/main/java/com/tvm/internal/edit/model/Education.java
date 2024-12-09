package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String coursePursued;
    private String institutionName;
    private int durationFrom;
    private int durationTo;
    private double cgpaObtained;
}