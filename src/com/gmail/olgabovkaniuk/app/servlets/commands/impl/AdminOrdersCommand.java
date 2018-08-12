package com.gmail.olgabovkaniuk.app.servlets.commands.impl;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;
import com.gmail.olgabovkaniuk.app.dao.model.Order;
import com.gmail.olgabovkaniuk.app.services.OrderService;
import com.gmail.olgabovkaniuk.app.services.impl.OrderServiceImpl;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;
import com.gmail.olgabovkaniuk.app.servlets.model.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminOrdersCommand implements Command {
    private OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Order> orderList = orderService.selectAllOrders();
        request.setAttribute("orders", orderList);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_ORDERS_PAGE_PATH);
    }
}
