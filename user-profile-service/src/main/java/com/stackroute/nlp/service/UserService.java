package com.stackroute.nlp.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsException;
}

