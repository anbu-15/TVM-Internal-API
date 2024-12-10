package com.tvm.internal.edit.repo;

import com.tvm.internal.edit.model.ProfessionalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalDataRepository extends JpaRepository<ProfessionalData, Long> {
}
