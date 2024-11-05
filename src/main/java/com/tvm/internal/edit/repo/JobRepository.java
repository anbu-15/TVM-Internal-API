package com.tvm.internal.edit.repo;

import com.tvm.internal.edit.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
