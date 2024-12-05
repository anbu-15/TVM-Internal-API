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
    @Column(name = "employee_reference")
    private String employeeReference;
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
    @Column(name = "t_name")
    private String tname;
    @Column(name = "t_relationship")
    private String trelationship;
    @Column(name = "t_age")
    private Integer tage;
    @Column(name = "t_oo")
    private String too;
    @Column(name = "language")
    private String language;
    @Column(name = "can_speak")
    private Boolean canSpeak;
    @Column(name = "can_Read")
    private Boolean canRead;
    @Column(name = "can_write")
    private Boolean canWrite;
}
