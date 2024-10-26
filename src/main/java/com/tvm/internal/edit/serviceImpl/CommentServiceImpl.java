package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Comment;
import com.tvm.internal.edit.repo.CommentRepository;
import com.tvm.internal.edit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        Comment existingComment = getCommentById(id);
        existingComment.setText(comment.getText());
        existingComment.setAuthor(comment.getAuthor());
        existingComment.setAuthorType(comment.getAuthorType());
        existingComment.setCreatedAt(comment.getCreatedAt());
        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
