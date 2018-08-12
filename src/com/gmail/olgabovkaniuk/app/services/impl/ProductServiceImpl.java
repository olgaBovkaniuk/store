package com.gmail.olgabovkaniuk.app.services.impl;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;
import com.gmail.olgabovkaniuk.app.dao.ProductDao;
import com.gmail.olgabovkaniuk.app.dao.connection.ConnectionService;
import com.gmail.olgabovkaniuk.app.dao.impl.ProductDaoImpl;
import com.gmail.olgabovkaniuk.app.dao.model.Product;
import com.gmail.olgabovkaniuk.app.services.ProductService;
import com.gmail.olgabovkaniuk.app.services.parser.ProductRecord;
import com.gmail.olgabovkaniuk.app.services.parser.ProductsFileParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> findAll() {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            List<Product> savedProductList = productDao.findAll(connection);
            connection.commit();
            return savedProductList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    public List<Product> findAllCall() {
        Callable<List<Product>> callable = new Callable<List<Product>>() {
            @Override
            public List<Product> call() throws Exception {
                List<Product> savedProductList = productDao.findAll(ConnectionService.getInstance().getConnection());
                return savedProductList;
            }
        };
        return doInTransaction(callable);
    }

    @Override
    public void save(Product product) {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            productDao.save(connection, product);
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void saveAll(List<Product> products) {
        for (Product product : products) {
            save(product);
        }
    }

    public void uploadProducts() {
        ProductsFileParser parser = new ProductsFileParser();
        List<Product> listNewProducts = parser.parse(ConfigurationManager.getInstance().getProperty(ConfigurationManager.UPLOAD_FILE_PATH));
        if (listNewProducts == null || listNewProducts.isEmpty()) {
            System.out.println("List of product is null or empty.");
            return;
        }
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            List<Product> listExistProduct = productDao.findAll(connection);
            Iterator<Product> iterator = listNewProducts.iterator();
            while (iterator.hasNext()) {
                Product newProduct = iterator.next();
                for (Product existProduct : listExistProduct) {
                    if (newProduct.equals(existProduct)) {
                        iterator.remove();
                        break;
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        saveAll(listNewProducts);
    }

    private <T> T doInTransaction(Callable<T> callable) {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            T result = callable.call();
            connection.commit();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
