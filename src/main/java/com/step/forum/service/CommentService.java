package com.step.forum.service;

import com.step.forum.model.Comment;

import java.util.List;

public interface CommentService {
    boolean addComment(Comment comment);
    List<Comment> getCommentByIdTopic(int id);
}
