package com.tvm.internal.edit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employees {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long employeeId;
        private String employeeName;
        private String details;
        private String role;
        private String profile;
        private String memberId;
        private String membername;
        private boolean activeStatus;
        private String memberCount;
        private String totalCount;

}
