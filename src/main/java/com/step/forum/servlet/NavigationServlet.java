package com.step.forum.servlet;

import com.step.forum.constants.NavigationConstants;
import com.step.forum.dao.TopicDaoImpl;
import com.step.forum.model.Comment;
import com.step.forum.model.Topic;
import com.step.forum.service.TopicService;
import com.step.forum.service.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NavigationServlet", urlPatterns = "/ns")
public class NavigationServlet extends HttpServlet {

    private TopicService topicService = new TopicServiceImpl(new TopicDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(response, request);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(response, request);
    }

    private void processRequest(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        String action = null;
        String address = null;

        if (request.getParameter("action") != null){
            action = request.getParameter("action");
        }else{
            response.sendRedirect("/");
            return;
        }


        if (action.equals(NavigationConstants.ACTION_TOPIC)){

            Integer topicID = Integer.parseInt(request.getParameter("id"));
            Topic topic = topicService.getTopicById(topicID);
            topic.setViewCount(topic.getViewCount() + 1);
            int viewCount = topic.getViewCount();
            boolean result = topicService.incrementTopicViewCount(topicID, viewCount);
            if (!result){
                System.err.println("Error View Count");
            }
            request.setAttribute("topic", topic);



            address = NavigationConstants.PAGE_TOPIC;

        }else if (action.equals(NavigationConstants.ACTION_NEW_TOPIC)){
            address = NavigationConstants.PAGE_NEW_TOPIC;
        }else if (action.equals(NavigationConstants.ACTION_REGISTER)){
            address = NavigationConstants.PAGE_REGISTER;
        }else if (action.equals(NavigationConstants.ACTION_LOGIN)){
            address = NavigationConstants.PAGE_LOGIN;
        }else if (action.equals(NavigationConstants.ACTION_REGISTER)){
            address = NavigationConstants.PAGE_REGISTER;
        }else if (action.equals(NavigationConstants.ACTION_INDEX)){
            address = NavigationConstants.PAGE_INDEX;
        }


        if (address != null){
            request.getRequestDispatcher(address).forward(request, response);
        }


    }
}
