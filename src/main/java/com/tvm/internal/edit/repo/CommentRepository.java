package com.tvm.internal.edit.repo;

import com.tvm.internal.edit.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
