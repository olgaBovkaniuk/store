package com.gmail.olgabovkaniuk.app.servlets.commands.impl;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;
import com.gmail.olgabovkaniuk.app.services.ProductService;
import com.gmail.olgabovkaniuk.app.services.impl.ProductServiceImpl;
import com.gmail.olgabovkaniuk.app.servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.gmail.olgabovkaniuk.app.config.ConfigurationManager.ADMIN_PRODUCTS_CMD_URL;

public class UploadProductsCommand implements Command {
    private ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        productService.uploadProducts();
        response.sendRedirect(ConfigurationManager.getInstance().getProperty(ADMIN_PRODUCTS_CMD_URL));
        return null;
    }
}
