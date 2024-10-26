package com.tvm.internal.edit.service;


import com.tvm.internal.edit.model.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);
    List<Comment> getAllComments();
    Comment getCommentById(Long id);
    Comment updateComment(Long id, Comment comment);
    void deleteComment(Long id);
}
