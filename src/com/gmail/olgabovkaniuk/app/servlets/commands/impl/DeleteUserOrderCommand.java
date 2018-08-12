package com.gmail.olgabovkaniuk.app.servlets.commands.impl;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;
import com.gmail.olgabovkaniuk.app.dao.model.Order;
import com.gmail.olgabovkaniuk.app.services.OrderService;
import com.gmail.olgabovkaniuk.app.services.UserService;
import com.gmail.olgabovkaniuk.app.services.impl.OrderServiceImpl;
import com.gmail.olgabovkaniuk.app.services.impl.UserServiceImpl;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;
import com.gmail.olgabovkaniuk.app.servlets.model.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

import static com.gmail.olgabovkaniuk.app.config.ConfigurationManager.USER_ORDERS_CMD_URL;

public class DeleteUserOrderCommand implements Command {
    private OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderId = request.getParameter("order_id");
        boolean deleted = orderService.delete(Long.valueOf(orderId));
        if (!deleted) {
            System.out.println("Order cannot be deleted by id, order id = " + orderId);
        }
        response.sendRedirect(ConfigurationManager.getInstance().getProperty(USER_ORDERS_CMD_URL));
        return null;
    }
}
