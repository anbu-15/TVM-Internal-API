package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "language")
    private String language;

    @Column(name = "can_speak")
    private Boolean speak;

    @Column(name = "can_read")
    private Boolean read;

    @Column(name = "can_write")
    private Boolean write;
}
