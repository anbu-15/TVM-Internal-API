package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;
    private String clientName;
    private String domain;
    private LocalDate startDate;
    private String voice;
    private LocalDate voiceStartDate;
    private LocalDate voiceEndDate;
    private String coding;
    private LocalDate codingStartDate;
    private LocalDate codingEndDate;
    private String projectStatus;

    @ElementCollection
    private List<String> asset;
}