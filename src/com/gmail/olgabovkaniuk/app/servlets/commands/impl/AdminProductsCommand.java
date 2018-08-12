package com.gmail.olgabovkaniuk.app.servlets.commands.impl;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;
import com.gmail.olgabovkaniuk.app.dao.model.Product;
import com.gmail.olgabovkaniuk.app.services.ProductService;
import com.gmail.olgabovkaniuk.app.services.impl.ProductServiceImpl;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminProductsCommand implements Command {
    private ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Product> productList = productService.findAll();
        request.setAttribute("products", productList);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PRODUCTS_PAGE_PATH);
    }
}
