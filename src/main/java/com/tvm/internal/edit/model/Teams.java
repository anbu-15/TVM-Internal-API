package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Entity
@Data
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId; // Required
    private String teamName; // Required
    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employees; // Optional
    @OneToMany(cascade = CascadeType.ALL)
    private List<Goal> goals; // Optional
}
