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
import java.math.BigDecimal;
import java.util.List;

public class AddOrderCommand implements Command {
    private OrderService orderService = new OrderServiceImpl();
    private ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long productId = Long.valueOf(request.getParameter("productId"));
        Product product = new Product();
        product.setId(productId);

        UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
        User user = User.newBuilder().withId(userPrincipal.getId()).build();

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setTotalPrice(BigDecimal.TEN);
        orderService.save(order);
        List<Product> productList = productService.findAll();
        request.setAttribute("products", productList);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_PRODUCTS_PAGE_PATH);
    }
}
