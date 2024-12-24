package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "age")
    private Integer age;

    @Column(name = "occupation")
    private String occupation;
}