package com.step.forum.servlet;

import com.step.forum.constants.MessageConstants;
import com.step.forum.constants.NavigationConstants;
import com.step.forum.dao.CommentDaoImpl;
import com.step.forum.model.Comment;
import com.step.forum.model.Topic;
import com.step.forum.model.User;
import com.step.forum.service.CommentService;
import com.step.forum.service.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CommentServlet", urlPatterns = "/cs")
public class CommentServlet extends HttpServlet {

    private CommentService commentService = new CommentServiceImpl(new CommentDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = null;

        if (request.getParameter("action") != null){
            action = request.getParameter("action");
        }else{
            response.sendRedirect("/");
            return;
        }


        if (action.equals(NavigationConstants.ACTION_ADD_COMMENT)){
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
                request.setAttribute("message", MessageConstants.SUCCESS_COMMENT_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("mes", MessageConstants.ERROR_MESSAGE_REPLY);
            }

            request.getRequestDispatcher(NavigationConstants.PAGE_TOPIC).forward(request, response);

        }else if (action.equals(NavigationConstants.ACTION_GET_COMMENTS_BY_TOPIC_ID)){
            System.out.println(request.getParameter("id"));
            int topicID = Integer.parseInt(request.getParameter("id"));
            List<Comment> list = null;
            try {
                list = commentService.getCommentByIdTopic(topicID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(list);
            request.setAttribute("list", list);
            request.getRequestDispatcher(NavigationConstants.PAGE_COMMENTS).forward(request, response);

        }
    }







}
