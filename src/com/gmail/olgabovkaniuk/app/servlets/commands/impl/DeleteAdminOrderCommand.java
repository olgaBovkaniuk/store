package com.gmail.olgabovkaniuk.app.servlets.commands.impl;

import com.gmail.olgabovkaniuk.app.services.OrderService;
import com.gmail.olgabovkaniuk.app.services.impl.OrderServiceImpl;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAdminOrderCommand implements Command {
    private OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderId = request.getParameter("order_id");
        boolean deleted = orderService.delete(orderId);
        if (!deleted) {
            System.out.println("Order cannot be deleted by id, order id = " + orderId);
        }
        response.sendRedirect("/dispatcher?command=admin_orders");
        return null;
    }
}
