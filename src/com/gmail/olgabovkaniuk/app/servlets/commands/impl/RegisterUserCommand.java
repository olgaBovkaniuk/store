package com.gmail.olgabovkaniuk.app.servlets.commands.impl;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;
import com.gmail.olgabovkaniuk.app.dao.model.User;
import com.gmail.olgabovkaniuk.app.dao.model.UserRoleEnum;
import com.gmail.olgabovkaniuk.app.services.UserService;
import com.gmail.olgabovkaniuk.app.services.impl.UserServiceImpl;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;
import com.gmail.olgabovkaniuk.app.servlets.util.UserPrincipalConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import static com.gmail.olgabovkaniuk.app.config.ConfigurationManager.LOGIN_CMD_URL;

public class RegisterUserCommand implements Command {
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String mobileNumber = request.getParameter("mobile_number");
        String additionalInfo = request.getParameter("additional_info");
        response.setContentType("text/html");

        if (email != null && !email.equals("") && firstName != null && lastName != null && password != null) {
            User userByUserName = userService.findUserByEmail(email);
            if (userByUserName != null) {
                request.setAttribute("error", "User with these username is already registered!");
                return ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTER_PAGE_PATH);
            }
        } else {
            request.setAttribute("error", "Please, fill all fields remarked by *");
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTER_PAGE_PATH);
        }
        userService.save(User.newBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withMobileNumber(mobileNumber)
                .withAdditionalInfo(additionalInfo)
                .withRole(UserRoleEnum.USER)
                .build()
        );
        response.sendRedirect(ConfigurationManager.getInstance().getProperty(LOGIN_CMD_URL));
        return null;
    }
}
