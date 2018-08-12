package com.gmail.olgabovkaniuk.app.dao.impl;

import com.gmail.olgabovkaniuk.app.dao.ProductDao;
import com.gmail.olgabovkaniuk.app.dao.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Product> findAll(Connection connection) {
        List<Product> productList = new ArrayList<>();
        String selectFromTableSql = "SELECT * FROM T_PRODUCT";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectFromTableSql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Product product = getProduct(resultSet);
                        productList.add(product);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return productList;
    }

    @Override
    public void save(Connection connection, Product product) {
        String saveToTableSql = "INSERT INTO T_PRODUCT (NAME, DESCRIPTION, PRICE) VALUES (?, ?, ?)";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(saveToTableSql)) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setBigDecimal(3, product.getPrice());
                preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public Product findById(Connection connection, Long productId) {
        String selectFromTableSql = "SELECT * FROM T_PRODUCT WHERE ID=?";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectFromTableSql)) {
                preparedStatement.setLong(1, productId);
                preparedStatement.execute();
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return getProduct(resultSet);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("ID"));
        product.setName(resultSet.getString("NAME"));
        product.setDescription(resultSet.getString("DESCRIPTION"));
        product.setPrice(resultSet.getBigDecimal("PRICE"));
        return product;
    }
}
