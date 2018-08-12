package com.gmail.olgabovkaniuk.app.dao;

import com.gmail.olgabovkaniuk.app.dao.model.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductDao {

    List<Product> findAll(Connection connection);

    void save(Connection connection, Product product);

    Product findById(Connection connection, Long productId);
}
