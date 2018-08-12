package com.gmail.olgabovkaniuk.app.servlets.commands.impl;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;
import com.gmail.olgabovkaniuk.app.dao.model.User;
import com.gmail.olgabovkaniuk.app.services.UserService;
import com.gmail.olgabovkaniuk.app.services.impl.UserServiceImpl;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;
import com.gmail.olgabovkaniuk.app.servlets.util.UserPrincipalConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.gmail.olgabovkaniuk.app.config.ConfigurationManager.LOGIN_CMD_URL;
import static com.gmail.olgabovkaniuk.app.config.ConfigurationManager.USERS_CMD_URL;
import static com.gmail.olgabovkaniuk.app.config.ConfigurationManager.USER_PRODUCTS_CMD_URL;

public class LoginCommand implements Command {
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email != null && !email.equals("")) {
            User userByUserName = userService.findUserByEmail(email);
            if (userByUserName != null) {
                if (userByUserName.getPassword().equals(password.trim())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", UserPrincipalConverter.toUserPrincipal(userByUserName));
                    switch (userByUserName.getRole()) {
                        case USER:
                            response.sendRedirect(ConfigurationManager.getInstance().getProperty(USER_PRODUCTS_CMD_URL));
                            break;
                        case ADMIN:
                            response.sendRedirect(ConfigurationManager.getInstance().getProperty(USERS_CMD_URL));
                            break;
                        default:
                            response.sendRedirect(ConfigurationManager.getInstance().getProperty(LOGIN_CMD_URL));
                            break;
                    }
                    return null;
                } else {
                    request.setAttribute("error", "Username or password is not correct!");
                    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
                }
            } else {
                request.setAttribute("error", "Username or password is not correct!");
                return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
            }
        } else {
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
    }
}
