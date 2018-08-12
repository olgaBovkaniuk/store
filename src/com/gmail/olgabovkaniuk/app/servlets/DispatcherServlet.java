package com.gmail.olgabovkaniuk.app.servlets;

import com.gmail.olgabovkaniuk.app.dao.connection.ConnectionService;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;
import com.gmail.olgabovkaniuk.app.servlets.commands.impl.*;
import com.gmail.olgabovkaniuk.app.servlets.model.CommandEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    private Map<CommandEnum, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commands.put(CommandEnum.LOGIN, new LoginCommand());
        commands.put(CommandEnum.USERS, new UsersCommand());
        commands.put(CommandEnum.REGISTER_USER, new RegisterUserCommand());
        commands.put(CommandEnum.USER_PRODUCTS, new UserProductsCommand());
        commands.put(CommandEnum.ADD_ORDER, new AddOrderCommand());
        commands.put(CommandEnum.USER_ORDERS, new UserOrdersCommand());
        commands.put(CommandEnum.DELETE_USER_ORDER, new DeleteUserOrderCommand());
        commands.put(CommandEnum.DELETE_ADMIN_ORDER, new DeleteAdminOrderCommand());
        commands.put(CommandEnum.ADMIN_PRODUCTS, new AdminProductsCommand());
        commands.put(CommandEnum.ADMIN_ORDERS, new AdminOrdersCommand());
        commands.put(CommandEnum.UPLOAD_XML, new UploadProductsCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String command = req.getParameter("command");
        Command commandAction = null;
        try {
            commandAction = commands.get(CommandEnum.getCommand(command));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if (commandAction != null) {
            try {
                String page = commandAction.execute(req, resp);
                if (page != null) {
                    getServletContext().getRequestDispatcher(page).forward(req, resp);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Command does not exist.");
        }
    }

    @Override
    public void destroy() {
        ConnectionService.getInstance().closeConnection();
    }
}
