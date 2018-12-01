package com.step.forum.servlet;

import com.mysql.cj.xdevapi.JsonArray;
import com.step.forum.constants.MessageConstants;
import com.step.forum.constants.NavigationConstants;
import com.step.forum.dao.CommentDaoImpl;
import com.step.forum.dao.TopicDaoImpl;
import com.step.forum.job.PopularTopicsUpdater;
import com.step.forum.model.Comment;
import com.step.forum.model.Topic;
import com.step.forum.model.User;
import com.step.forum.service.CommentService;
import com.step.forum.service.CommentServiceImpl;
import com.step.forum.service.TopicService;
import com.step.forum.service.TopicServiceImpl;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TopicServlet", urlPatterns = "/ts")
public class TopicServlet extends HttpServlet {

    private TopicService topicService = new TopicServiceImpl(new TopicDaoImpl());
    private CommentService commentService = new CommentServiceImpl(new CommentDaoImpl());
    private PopularTopicsUpdater updater;

    @Override
    public void init() throws ServletException {
        updater = new PopularTopicsUpdater(topicService);
        updater.startJob();
    }

    @Override
    public void destroy() {
        updater.stopJob();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = null;
        String address = null;

        if (request.getParameter("action") != null){
            action = request.getParameter("action");
        }else{
            response.sendRedirect("/");
            return;
        }

        if (action.equals(NavigationConstants.ACTION_NEW_TOPIC)){
            String title = request.getParameter("new-title");
            String desc = request.getParameter("new-desc");
            User userID = (User) request.getSession().getAttribute("user");

            Topic topic = new Topic();
            topic.setTitle(title);
            topic.setDesc(desc);
            topic.setUser(userID);

            try {
                topicService.newTopic(topic);
                request.setAttribute("message", MessageConstants.SUCCESS_TOPIC_ADD_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_TOPIC_ADD);
            }
            request.getRequestDispatcher(NavigationConstants.PAGE_NEW_TOPIC).forward(request, response);

        }else if (action.equals(NavigationConstants.ACTION_TOPIC)){
            String reply = request.getParameter("reply");
            User userID = (User) request.getSession().getAttribute("user");
            Integer topicID = Integer.valueOf(request.getParameter("id"));
            Topic t = new Topic();
            t.setId(topicID);
            Comment c = new Comment();
            c.setDesc(reply);
            c.setUser(userID);
            c.setTopic(t);
            System.out.println(c);

            try {
                commentService.addComment(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else if (action.equals(NavigationConstants.ACTION_GET_POPULAR_TOPICS)){
            List<Topic> listTopics = updater.getPopularTopics();
            JSONArray jsonArray = new JSONArray(listTopics);
            response.setContentType("application/json");
            response.getWriter().write(jsonArray.toString());

        }


        if (address != null){
            request.getRequestDispatcher(address).forward(request, response);
        }
    }
}















