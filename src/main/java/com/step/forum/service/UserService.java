package com.step.forum.service;

import com.step.forum.exceptions.DuplicateEmailException;
import com.step.forum.exceptions.InactiveAccountException;
import com.step.forum.exceptions.InvalidEmailException;
import com.step.forum.exceptions.InvalidPasswordException;
import com.step.forum.model.User;

public interface UserService {
    boolean register(User user) throws DuplicateEmailException;
    User login(String email, String password) throws InvalidEmailException, InactiveAccountException, InvalidPasswordException;

}
