package com.step.forum.dao;

import com.step.forum.model.Comment;

import java.util.List;

public interface CommentDAO {
    boolean addComment(Comment comment);
    List<Comment> getCommentByIdTopic(int id);
}
