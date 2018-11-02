package com.step.forum.filter;

import com.step.forum.constants.UserConstants;
import com.step.forum.dao.UserDaoImpl;
import com.step.forum.model.Action;
import com.step.forum.model.User;
import com.step.forum.service.UserService;
import com.step.forum.service.UserServiceImpl;
import org.omg.CORBA.ServerRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

//@WebFilter(filterName = "SecurityFilter", urlPatterns = "/*")
public class SecurityFilter {
    private UserService userService = new UserServiceImpl(new UserDaoImpl());

    private List<Action> adminAction;
    private List<Action> managerAction;
    private List<Action> nonAuthAction;

    public void destroy(){

    }

    public void doFilter(ServletRequest serverRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException{
        HttpServletRequest request = (HttpServletRequest) serverRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getRequestURI().contains("resources")){
            chain.doFilter(serverRequest, servletResponse);
            return;
        }

        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");
        boolean isAllowed = false;

        if (action != null){
            List<Action> list = new ArrayList<>();

            if (user == null){
                list = nonAuthAction;
            }else if (user.getRole().getId() == UserConstants.USER_ROLE_ADMIN){
                list = adminAction;
            }else if (user.getRole().getId() == UserConstants.USER_ROLE_MANAGER){
                list = managerAction;
            }

            isAllowed = list.stream().anyMatch(a -> a.getActionType().equalsIgnoreCase(action));

        }else if (request.getRequestURI().equals(request.getContextPath() + "/")){
            isAllowed = (user != null);
        }

        if (isAllowed){
            chain.doFilter(serverRequest, servletResponse);
        }else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Location", "/ns?action=login");

            if (!"XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))){
                response.sendRedirect("/ns?action=login");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }


}
