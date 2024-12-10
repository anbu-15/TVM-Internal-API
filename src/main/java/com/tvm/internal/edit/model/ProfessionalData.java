package com.tvm.internal.edit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProfessionalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String durationFrom;
    private String durationTo;
    private String empPreference;
    private String role;
    private String type;
    private String achievements;
    private String physical;
    private String arrest;
    private String employment;
    private String surgical;
}
