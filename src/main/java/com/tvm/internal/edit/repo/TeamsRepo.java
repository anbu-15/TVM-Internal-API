package com.tvm.internal.edit.repo;

import com.tvm.internal.edit.model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepo extends JpaRepository<Teams,Integer> {
}
