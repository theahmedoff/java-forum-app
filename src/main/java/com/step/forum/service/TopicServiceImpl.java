package com.step.forum.service;

import com.step.forum.dao.TopicDAO;
import com.step.forum.dao.TopicDaoImpl;
import com.step.forum.model.Topic;

import java.sql.SQLException;
import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDao;

    public TopicServiceImpl(TopicDAO topicDao) {
        this.topicDao = topicDao;
    }

    @Override
    public List<Topic> getAllTopic()throws SQLException {
        return topicDao.getAllTopic();
    }

    @Override
    public Topic getTopicById(int id)throws SQLException {
        return topicDao.getTopicById(id);
    }

    @Override
    public void incrementTopicViewCount(int id, int count)throws SQLException {
        topicDao.incrementTopicViewCount(id, count);
    }

    @Override
    public void newTopic(Topic topic)throws SQLException {
        topicDao.newTopic(topic);
    }

    @Override
    public List<Topic> getPopularTopic()throws SQLException {
        return topicDao.getPopularTopic();
    }
}
