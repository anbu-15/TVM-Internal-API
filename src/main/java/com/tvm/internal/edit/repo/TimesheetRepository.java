package com.tvm.internal.edit.repo;

import com.tvm.internal.edit.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
}
