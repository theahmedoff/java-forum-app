package com.step.forum.service;

import com.step.forum.dao.CommentDAO;
import com.step.forum.model.Comment;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentDAO commentDAO;

    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentDAO.addComment(comment);
    }

    @Override
    public List<Comment> getCommentByIdTopic(int id) {
        return commentDAO.getCommentByIdTopic(id);
    }
}
