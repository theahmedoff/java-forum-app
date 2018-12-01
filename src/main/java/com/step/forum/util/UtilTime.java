package com.step.forum.util;

import com.step.forum.dao.TopicDaoImpl;
import com.step.forum.model.Topic;
import com.step.forum.service.TopicService;
import com.step.forum.service.TopicServiceImpl;

import java.sql.SQLException;
import java.time.*;
import java.time.chrono.ChronoPeriod;
import java.util.ArrayList;
import java.util.List;

public class UtilTime {

    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;


    public static void main(String[] args) {
        LocalDateTime toDateTime = LocalDateTime.of(2018, 10, 22, 3, 6, 45);
        LocalDateTime timeNow = LocalDateTime.now();
        Period period = getPeriod(toDateTime, timeNow);
        long time[] = getTime(toDateTime, timeNow);

        System.out.println(period.getYears() + " years " +
                period.getMonths() + " months " +
                period.getDays() + " days " +
                time[0] + " hours " +
                time[1] + " minutes " +
                time[2] + " seconds.");

        System.out.println(timeNow);


        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(time[0]);
        if (time[1] != 0){
            System.out.println(time[1]);
        }
        System.out.println(time[2]);

        System.out.println("Time: " + topicDate(timeNow));

        TopicService topicService = new TopicServiceImpl(new TopicDaoImpl());
        List<Topic> list = new ArrayList<>();
        try {
            list = topicService.getAllTopic();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Topic topic : list){
            System.out.println(topicDate(topic.getShareDate()));
        }

    }

    public static String topicDate(LocalDateTime date){
        //LocalDateTime toDateTime = LocalDateTime.of(2018, 10, 22, 3, 6, 45);
        LocalDateTime timeNow = LocalDateTime.now();
        Period period = getPeriod(date, timeNow);
        long time[] = getTime(date, timeNow);

        String getTime = "";

        if (period.getYears() != 0){
            getTime = period.getYears() + " years";
        }else if (period.getMonths() != 0){
            getTime = period.getMonths() + " months";
        }else if (period.getDays() != 0){
            getTime = period.getDays() + " days";
        }else if (time[0] != 0){
            getTime = time[0] + " hours";
        }else if (time[1] != 0){
            getTime = time[1] + " minutes";
        }else if (time[2] != 0){
            getTime = time[2] + " seconds";
        }
        return getTime;
    }

    private static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
        return Period.between(dob.toLocalDate(), now.toLocalDate());
    }

    private static long[] getTime(LocalDateTime dob, LocalDateTime now) {
        LocalDateTime today = LocalDateTime.of(now.getYear(),
                now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        Duration duration = Duration.between(today, now);

        long seconds = duration.getSeconds();

        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);

        return new long[]{hours, minutes, secs};
    }
}
