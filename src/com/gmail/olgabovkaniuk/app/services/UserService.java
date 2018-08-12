package com.gmail.olgabovkaniuk.app.services;

import com.gmail.olgabovkaniuk.app.dao.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findUserByEmail(String email);

    List<User> findAll();

}
