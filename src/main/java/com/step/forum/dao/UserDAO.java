package com.step.forum.dao;

import com.step.forum.exceptions.DuplicateEmailException;
import com.step.forum.exceptions.InactiveAccountException;
import com.step.forum.exceptions.InvalidEmailException;
import com.step.forum.exceptions.InvalidPasswordException;
import com.step.forum.model.User;

import java.util.List;

public interface UserDAO {
    boolean register(User user) throws DuplicateEmailException;
    User login(String email, String password) throws InvalidEmailException, InactiveAccountException, InvalidPasswordException;
}
