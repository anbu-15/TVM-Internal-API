package com.tvm.internal.edit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "announcements")
public class Announcements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "message", length = 500)
    private String message;

    @Lob
    @Column(name = "attachment",columnDefinition = "BLOB")
    private byte[] attachment;

    @Column(name = "category")
    private String category;

    @Column(name = "expiry")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expiry;

    @Column(name = "location")
    private String location;

    @Column(name = "notifyAll")
    private boolean notifyAll;

    @Column(name = "pinAllAnnouncement")
    private boolean pinAllAnnouncement;

    @Column(name = "disableComments")
    private boolean disableComments;
}