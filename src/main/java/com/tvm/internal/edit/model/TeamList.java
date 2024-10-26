package com.tvm.internal.edit.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "team_list")
@Data
public class TeamList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamMemberId;

    private String firstName;
    private String lastName;
    private String nickName;
    private String emailAddress;
    //    private String photo;
    @Lob
    private byte[] photo;
    private String department;
    private String designation;
    private String role;
    private String employmentType;
    private String presentAddress;
    private String aadharNumber;
    private String pan;
    private String uan;
}
