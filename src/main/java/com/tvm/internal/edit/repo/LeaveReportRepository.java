package com.tvm.internal.edit.repo;


import com.tvm.internal.edit.model.LeaveReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveReportRepository extends JpaRepository<LeaveReport, Long> {
    // Custom query methods can be defined here if needed
}


