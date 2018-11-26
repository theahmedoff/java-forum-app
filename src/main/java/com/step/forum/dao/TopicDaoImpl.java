package com.step.forum.dao;

import com.step.forum.constants.TopicConstants;
import com.step.forum.model.Comment;
import com.step.forum.model.Topic;
import com.step.forum.model.User;
import com.step.forum.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class TopicDaoImpl implements TopicDAO {
    private final String GET_ALL_TOPIC_SQL = "select t.id_topic, t.title, t.description, t.share_date, t.view_count, t.status, u.id_user, u.email, u.first_name, u.last_name, u.img, c.id_comment, c.description, c.write_date from topic t inner join user u on t.id_user = u.id_user left join comment c on c.id_topic = t.id_topic where t.status = ? order by t.share_date desc";
    private final String GET_TOPIC_BY_ID_SQL = "select t.id_topic, t.title, t.description as topicDesc, t.share_date, t.view_count, u.id_user as tID_user, u.first_name as tFirst_name, u.last_name as tLast_name, u.img, c.id_comment, c.description as commentDesc, c.write_date, us.id_user as cID_user, us.first_name as cFirst_name, us.last_name as cLast_name us.img, from topic t inner join user u on t.id_user = u.id_user left join  comment c on c.id_topic = t.id_topic left join user us on c.id_user = us.id_user where t.id_topic = ? and t.status = 1";
    private final String INCRIMENT_TOPIC_VIEW_COUNT_BY_ID = "update topic set view_count = ? where id_topic = ?";
    private final String SET_NEW_TOPIC_SQL = "insert into topic(title, description, share_date, view_count, id_user, status) values(?, ?, ?, ?, ?, ?)";
    private final String GET_POPULAR_TOPICS_SQL = "select t.id_topic, t.title, count(c.id_comment) as comments from topic t left join comment c on t.id_topic=c.id_topic group by t.title having comments>0  order by comments desc limit 7";

    @Override
    public List<Topic> getAllTopic() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Topic> list = new ArrayList<>();

        try{
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_ALL_TOPIC_SQL);
            ps.setInt(1, TopicConstants.TOPIC_STATUS_ACTIVE);
            rs = ps.executeQuery();
            Map<Integer, Topic> map = new LinkedHashMap<>();
            while (rs.next()){
                Topic t = map.get(rs.getInt("id_topic"));

                if (t == null){
                    t = new Topic();
                    t.setId(rs.getInt("id_topic"));
                    t.setTitle(rs.getString("title"));
                    t.setDesc(rs.getString("description"));
                    t.setShareDate(rs.getTimestamp("share_date").toLocalDateTime());
                    t.setViewCount(rs.getInt("view_count"));

                    User u = new User();
                    u.setId(rs.getInt("id_user"));
                    u.setEmail(rs.getString("email"));
                    u.setFirstName(rs.getString("first_name"));
                    u.setLastName(rs.getString("last_name"));
                    u.setImagePath(rs.getString("img"));
                    t.setUser(u);
                    map.put(t.getId(), t);
                }

                if (rs.getInt("id_comment") != 0){
                    Comment c = new Comment();
                    c.setId(rs.getInt("id_comment"));
                    c.setDesc(rs.getString("description"));
                    c.setWriteDate(rs.getTimestamp("write_date").toLocalDateTime());
                    t.addComment(c);
                }
            }

            list = new ArrayList<>(map.values());


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con, ps, rs);
        }

        return list;
    }

    @Override
    public Topic getTopicById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Topic topic = null;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_TOPIC_BY_ID_SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Map<Integer, Topic> map = new LinkedHashMap<>();

            while (rs.next()) {
                topic = map.get(rs.getInt("id_topic"));

                if (topic == null) {
                    topic = new Topic();
                    topic.setId(rs.getInt("id_topic"));
                    topic.setTitle(rs.getString("title"));
                    topic.setDesc(rs.getString("topicDesc"));
                    topic.setShareDate(rs.getTimestamp("share_date").toLocalDateTime());
                    topic.setViewCount(rs.getInt("view_count"));

                    User topicUser = new User();
                    topicUser.setId(rs.getInt("tID_user"));
                    topicUser.setFirstName(rs.getString("tFirst_name"));
                    topicUser.setLastName(rs.getString("tLast_name"));
                    topicUser.setImagePath(rs.getString("img"));
                    topic.setUser(topicUser);

                    topic.setUser(topicUser);
                    map.put(topic.getId(), topic);
                }

                if (rs.getInt("id_comment") != 0) {
                    Comment comment = new Comment();
                    comment.setId(rs.getInt("id_comment"));
                    comment.setDesc(rs.getString("commentDesc"));
                    comment.setWriteDate(rs.getTimestamp("write_Date").toLocalDateTime());

                    User commentUser = new User();
                    commentUser.setId(rs.getInt("cID_user"));
                    commentUser.setFirstName(rs.getString("cFirst_name"));
                    commentUser.setLastName(rs.getString("cLast_name"));
                    commentUser.setImagePath(rs.getString("img"));
                    comment.setUser(commentUser);

                    topic.addComment(comment);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DbUtil.closeAll(con, ps, rs);
        }

        return topic;

    }

    @Override
    public boolean incrementTopicViewCount(int id, int count) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(INCRIMENT_TOPIC_VIEW_COUNT_BY_ID);
            ps.setInt(1, count);
            ps.setInt(2, id);
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
    public boolean newTopic(Topic topic) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(SET_NEW_TOPIC_SQL);
            ps.setString(1, topic.getTitle());
            ps.setString(2, topic.getDesc());
            ps.setString(3, LocalDateTime.now().toString());
            ps.setInt(4, 0);
            ps.setInt(5, topic.getUser().getId());
            ps.setInt(6, TopicConstants.TOPIC_STATUS_INACTIVE);
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
    public List<Topic> getPopularTopic() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Topic> list = new ArrayList<>();
        try{
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_POPULAR_TOPICS_SQL);
            rs = ps.executeQuery();
            while (rs.next()){
                Topic t = new Topic();
                t.setId(rs.getInt("id_topic"));
                t.setTitle(rs.getString("title"));
                t.setCommentCount(rs.getInt("comments"));
                list.add(t);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con, ps, rs);
        }
        return list;
    }

}
