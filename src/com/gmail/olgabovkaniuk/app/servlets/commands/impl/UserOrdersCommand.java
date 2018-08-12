package com.gmail.olgabovkaniuk.app.servlets.commands.impl;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;
import com.gmail.olgabovkaniuk.app.dao.model.Order;
import com.gmail.olgabovkaniuk.app.dao.model.User;
import com.gmail.olgabovkaniuk.app.services.OrderService;
import com.gmail.olgabovkaniuk.app.services.UserService;
import com.gmail.olgabovkaniuk.app.services.impl.OrderServiceImpl;
import com.gmail.olgabovkaniuk.app.services.impl.UserServiceImpl;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;
import com.gmail.olgabovkaniuk.app.servlets.model.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserOrdersCommand implements Command {
    private OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
        List<Order> orderList = orderService.findUserOrders(userPrincipal.getId());
        request.setAttribute("orders", orderList);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_ORDERS_PAGE_PATH);
    }
}
