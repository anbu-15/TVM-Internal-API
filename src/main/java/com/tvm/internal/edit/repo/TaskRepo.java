package com.tvm.internal.edit.repo;

import com.tvm.internal.edit.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository <Tasks, Integer> {

}
