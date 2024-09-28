package com.tvm.internal.edit.repo;

import com.tvm.internal.edit.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStatus(String status);
}
