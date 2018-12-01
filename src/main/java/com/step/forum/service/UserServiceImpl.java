package com.step.forum.service;

import com.step.forum.dao.UserDAO;
import com.step.forum.exceptions.DuplicateEmailException;
import com.step.forum.exceptions.InactiveAccountException;
import com.step.forum.exceptions.InvalidEmailException;
import com.step.forum.exceptions.InvalidPasswordException;
import com.step.forum.model.User;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void register(User user) throws SQLException, DuplicateEmailException {
        userDAO.register(user);
    }

    @Override
    public User login(String email, String password) throws SQLException, InvalidEmailException, InactiveAccountException, InvalidPasswordException {
        return userDAO.login(email, password);
    }
}
