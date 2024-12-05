package com.tvm.internal.edit.repo;

import com.tvm.internal.edit.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
