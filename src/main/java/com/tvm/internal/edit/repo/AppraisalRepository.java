package com.tvm.internal.edit.repo;

import com.tvm.internal.edit.model.Appraisal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppraisalRepository extends JpaRepository<Appraisal, UUID> {
}
