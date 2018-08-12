package com.gmail.olgabovkaniuk.app.servlets.commands.impl;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;
import com.gmail.olgabovkaniuk.app.dao.model.User;
import com.gmail.olgabovkaniuk.app.services.UserService;
import com.gmail.olgabovkaniuk.app.services.impl.UserServiceImpl;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UsersCommand implements Command {
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> userList = userService.findAll();
        request.setAttribute("users", userList);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USERS_PAGE_PATH);
    }
}
