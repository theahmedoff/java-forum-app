package com.step.forum.dao;

import com.step.forum.exceptions.DuplicateEmailException;
import com.step.forum.exceptions.InactiveAccountException;
import com.step.forum.exceptions.InvalidEmailException;
import com.step.forum.exceptions.InvalidPasswordException;
import com.step.forum.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void register(User user) throws DuplicateEmailException, SQLException;
    User login(String email, String password) throws InvalidEmailException, InactiveAccountException, InvalidPasswordException, SQLException;
}
