package com.gmail.olgabovkaniuk.app.servlets.commands.impl;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;
import com.gmail.olgabovkaniuk.app.services.OrderService;
import com.gmail.olgabovkaniuk.app.services.impl.OrderServiceImpl;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.gmail.olgabovkaniuk.app.config.ConfigurationManager.ADMIN_ORDERS_CMD_URL;

public class DeleteAdminOrderCommand implements Command {
    private OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderId = request.getParameter("order_id");
        boolean deleted = orderService.delete(Long.valueOf(orderId));
        if (!deleted) {
            System.out.println("Order cannot be deleted by id, order id = " + orderId);
        }
        response.sendRedirect(ConfigurationManager.getInstance().getProperty(ADMIN_ORDERS_CMD_URL));
        return null;
    }
}
