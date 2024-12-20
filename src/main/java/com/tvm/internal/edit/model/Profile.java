package com.tvm.internal.edit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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
    @Column(name = "Date_of_Birth")
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
    @Column(name = "mobile_number")
    private Long mobileNumber;
    @Column(name = "home_number")
    private Long homeNumber;
    @Column(name = "office_number")
    private Long officeNumber;
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
    private Integer pincode;
    @Column(name = "passport")
    private String passport;
    @Column(name = "landmark")
    private String landmark;
    @Column(name = "visa")
    private String visa;
    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @Column(name = "name")
    private String name;
    @Column(name = "t_relationship")
    private String relationship;
    @Column(name = "t_age")
    private Integer age;
    @Column(name = "t_oo")
    private String Occupation;
    @Column(name = "language")
    private String language;
    @Column(name = "can_speak")
    private Boolean speak;
    @Column(name = "can_Read")
    private Boolean Read;
    @Column(name = "can_write")
    private Boolean Write;
}