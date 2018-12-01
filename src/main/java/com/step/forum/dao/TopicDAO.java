package com.step.forum.dao;

import com.step.forum.model.Topic;

import java.sql.SQLException;
import java.util.List;

public interface TopicDAO {
    List<Topic> getAllTopic() throws SQLException;
    Topic getTopicById(int id) throws SQLException;
    void incrementTopicViewCount(int id, int count) throws SQLException;
    void newTopic(Topic topic) throws SQLException;
    List<Topic> getPopularTopic() throws SQLException;

}
