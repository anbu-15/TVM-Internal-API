package com.tvm.internal.edit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dob;

    @Column(name = "position")
    private String position;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Lob
    @Column(name = "employee_photo", columnDefinition = "LONGBLOB")
    private String employeePhoto;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "home_number")
    private Long homeNumber;

    @Column(name = "emergency_number")
    private Long emergencyNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "present_address")
    private String presentAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "locality")
    private String locality;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "passport")
    private String passport;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "visa")
    private String visa;

    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private List<FamilyMember> familyMembers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private List<Language> languages;
}
