package com.step.forum.model;

import com.step.forum.util.UtilTime;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class Topic {
    private int id;
    private String title;
    private String desc;
    private LocalDateTime shareDate;
    private int viewCount;
    private User user;
    private List<Comment> commentList;

    public Topic(){
        commentList = new ArrayList<>();
    }

    public void addComment(Comment comment){
        commentList.add(comment);
    }

    public String getTopicTime() {
        String date = UtilTime.topicDate(shareDate);
        return date;
    }
}
