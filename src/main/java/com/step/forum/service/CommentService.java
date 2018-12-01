package com.step.forum.service;

import com.step.forum.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    void addComment(Comment comment) throws SQLException;
    List<Comment> getCommentByIdTopic(int id) throws SQLException;
}
