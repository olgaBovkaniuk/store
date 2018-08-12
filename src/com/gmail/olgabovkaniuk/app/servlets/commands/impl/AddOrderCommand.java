package com.gmail.olgabovkaniuk.app.servlets.commands.impl;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;
import com.gmail.olgabovkaniuk.app.dao.model.Order;
import com.gmail.olgabovkaniuk.app.dao.model.Product;
import com.gmail.olgabovkaniuk.app.dao.model.User;
import com.gmail.olgabovkaniuk.app.services.OrderService;
import com.gmail.olgabovkaniuk.app.services.ProductService;
import com.gmail.olgabovkaniuk.app.services.impl.OrderServiceImpl;
import com.gmail.olgabovkaniuk.app.services.impl.ProductServiceImpl;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;
import com.gmail.olgabovkaniuk.app.servlets.model.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.gmail.olgabovkaniuk.app.config.ConfigurationManager.USER_PRODUCTS_CMD_URL;

public class AddOrderCommand implements Command {
    private OrderService orderService = new OrderServiceImpl();
    private ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long productId = Long.valueOf(request.getParameter("product_id"));

        Product product = productService.findById(productId);

        UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
        User user = User.newBuilder().withId(userPrincipal.getId()).build();

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setTotalPrice(product.getPrice());
        orderService.save(order);
        response.sendRedirect(ConfigurationManager.getInstance().getProperty(USER_PRODUCTS_CMD_URL));
        return null;
    }
}
