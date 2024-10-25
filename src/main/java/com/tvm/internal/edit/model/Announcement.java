package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ANNOUNCEMENT_ID")
    private Long Id;
    @Column(name = "ANNOUNCEMENT_NAME")
    private String name;
    @Column(name = "ANNOUNCEMENT_TITLE")
    private String title;
    @Column(name = "ANNOUNCEMENT_MESSAGE")
    private String message;
    @Column(name = "ANNOUNCEMENT_ATTACHMENT")
    private String attachment;
    @Column(name = "ANNOUNCEMENT_CATEGORY")
    private String category;
    @Column(name = "ANNOUNCEMENT_EXPIRY")
    private LocalDate expiry;
    @Column(name = "ANNOUNCEMENT_LOCATION")
    private String location;
}