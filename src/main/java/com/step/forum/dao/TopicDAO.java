package com.step.forum.dao;

import com.step.forum.model.Topic;

import java.util.List;

public interface TopicDAO {
    List<Topic> getAllTopic();
    Topic getTopicById(int id);
    boolean incrementTopicViewCount(int id, int count);
    boolean newTopic(Topic topic);
    List<Topic> getPopularTopic();

}
