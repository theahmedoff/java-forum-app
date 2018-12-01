package com.step.forum.service;

import com.step.forum.dao.CommentDAO;
import com.step.forum.model.Comment;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentDAO commentDAO;

    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public void addComment(Comment comment) throws SQLException {
        commentDAO.addComment(comment);
    }

    @Override
    public List<Comment> getCommentByIdTopic(int id) throws SQLException {
        return commentDAO.getCommentByIdTopic(id);
    }
}
