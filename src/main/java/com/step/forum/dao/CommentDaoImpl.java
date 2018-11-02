package com.step.forum.dao;

import com.step.forum.model.Comment;
import com.step.forum.model.User;
import com.step.forum.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDAO{

    private final String SET_COMMENT_SQL = "insert into comment(description, write_date, id_topic, id_user) values(?, ?, ?, ?)";
    private final String GET_COMMENT_BY_ID_TOPIC_SQL = "select c.id_comment, c.description, c.write_date, u.id_user, u.first_name, u.last_name from comment c inner join user u on c.id_user = u.id_user where c.id_topic = ?";

    @Override
    public boolean addComment(Comment comment) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(SET_COMMENT_SQL);
            ps.setString(1, comment.getDesc());
            ps.setString(2, LocalDateTime.now().toString());
            ps.setInt(3, comment.getTopic().getId());
            ps.setInt(4, comment.getUser().getId());
            ps.executeUpdate();
            result = true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con, ps);
        }
        return result;
    }

    @Override
    public List<Comment> getCommentByIdTopic(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Comment> list = new ArrayList<>();
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_COMMENT_BY_ID_TOPIC_SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                Comment c = new Comment();
                c.setId(rs.getInt("id_comment"));
                c.setDesc(rs.getString("description"));
                c.setWriteDate(rs.getTimestamp("write_date").toLocalDateTime());
                User u = new User();
                u.setId(rs.getInt("id_user"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                c.setUser(u);
                list.add(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con, ps, rs);
        }
        return list;
    }
}
