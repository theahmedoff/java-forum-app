package com.step.forum.servlet;

import com.step.forum.constants.MessageConstants;
import com.step.forum.constants.NavigationConstants;
import com.step.forum.constants.UserConstants;
import com.step.forum.dao.UserDaoImpl;
import com.step.forum.exceptions.DuplicateEmailException;
import com.step.forum.exceptions.InactiveAccountException;
import com.step.forum.exceptions.InvalidEmailException;
import com.step.forum.exceptions.InvalidPasswordException;
import com.step.forum.model.Role;
import com.step.forum.model.User;
import com.step.forum.service.UserService;
import com.step.forum.service.UserServiceImpl;
import com.step.forum.util.CryptoUtil;
import com.step.forum.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.DataInput;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.UUID;

@WebServlet(name = "UserServlet", urlPatterns = "/us")
public class UserServlet extends HttpServlet {

    UserService userService = new UserServiceImpl(new UserDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest (HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        String acion = null;


        if (request.getParameter("action") != null){
            acion = request.getParameter("action");
        }else {
            response.sendRedirect("/");
            return;
        }



        if(acion.equals(NavigationConstants.ACTION_REGISTER)){

            String firsName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String email = request.getParameter("email");
            String password = request.getParameter("pass");

            if (!ValidationUtil.validate(firsName, lastName, email, password)){
                request.setAttribute("message", MessageConstants.ERROR_MESSAGE_VALIDATE);
                request.getRequestDispatcher(NavigationConstants.PAGE_REGISTER);
            }

            User user = new User();
            user.setFirstName(firsName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(CryptoUtil.inputToHash(password));
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            Role role = new Role();
            role.setId(2);
            user.setRole(role);
            user.setStatus(UserConstants.USER_ROLE_MANAGER);

            try {
                boolean result = userService.register(user);
                if (result){
                    System.out.println(user);
                    request.setAttribute("message", MessageConstants.SUCCESS_REGISTER_MESSAGE);
                    request.getRequestDispatcher(NavigationConstants.PAGE_LOGIN).forward(request, response);
                }
            }catch (DuplicateEmailException e){
                e.printStackTrace();
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher(NavigationConstants.PAGE_REGISTER);
            }


        }else if (acion.equals(NavigationConstants.ACTION_DO_LOGIN)){
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            try {

                User user = userService.login(email, CryptoUtil.inputToHash(password));

                if (user != null){
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect("/");
                }
            }catch (InvalidEmailException | InvalidPasswordException | InactiveAccountException e){
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher(NavigationConstants.PAGE_LOGIN).forward(request, response);
            }
        }else if(acion.equalsIgnoreCase(NavigationConstants.ACTION_LOGOUT)){
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null){
                session.removeAttribute("user");
                session.invalidate();
                response.sendRedirect("/");
            }
        }

    }
}