package com.gmail.olgabovkaniuk.app.dao.impl;

import com.gmail.olgabovkaniuk.app.dao.OrderDao;
import com.gmail.olgabovkaniuk.app.dao.model.Order;
import com.gmail.olgabovkaniuk.app.dao.model.Product;
import com.gmail.olgabovkaniuk.app.dao.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public Order save(Connection connection, Order order) {
        String saveIntoTableSql = "INSERT INTO T_ORDER (ORDER_NUMBER, USER_ID, PRODUCT_ID, TOTAL_PRICE) VALUES (?, ?, ?, ?)";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(saveIntoTableSql)) {
                preparedStatement.setString(1, order.getOrderNumber());
                preparedStatement.setLong(2, order.getUser().getId());
                preparedStatement.setLong(3, order.getProduct().getId());
                preparedStatement.setBigDecimal(4, order.getTotalPrice());
                preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public List<Order> findUserOrders(Connection connection, Long userId) {
        List<Order> orderList = new ArrayList<>();
        String selectFromTableSql = "SELECT " +
                "    o.ID as orderId, " +
                "    o.ORDER_NUMBER, " +
                "    o.USER_ID, " +
                "    u.FIRST_NAME, " +
                "    u.LAST_NAME, " +
                "    p.ID as productId,  " +
                "    p.NAME," +
                "    p.DESCRIPTION," +
                "    p.PRICE " +
                "FROM T_ORDER o " +
                "INNER JOIN T_PRODUCT p ON o.PRODUCT_ID = p.ID " +
                "INNER JOIN T_USER u ON o.USER_ID = u.ID " +
                "WHERE o.USER_ID=?";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectFromTableSql)) {
                preparedStatement.setLong(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Order order = getOrder(resultSet);
                        orderList.add(order);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return orderList;
    }

    @Override
    public boolean delete(Connection connection, String orderId) {
        String deleteFromTableSql = "DELETE FROM T_ORDER WHERE id=" + orderId;
        if (connection != null) {
            try (Statement preparedStatement = connection.createStatement()) {
                preparedStatement.execute(deleteFromTableSql);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public List<Order> selectAllOrders(Connection connection) {
        List<Order> orderList = new ArrayList<>();
        String selectFromTableSql = "SELECT " +
                "    o.ID as orderId, " +
                "    o.ORDER_NUMBER, " +
                "    o.USER_ID, " +
                "    u.FIRST_NAME, " +
                "    u.LAST_NAME, " +
                "    p.ID as productId,  " +
                "    p.NAME," +
                "    p.DESCRIPTION," +
                "    p.PRICE " +
                "FROM T_ORDER o " +
                "INNER JOIN T_PRODUCT p ON o.PRODUCT_ID = p.ID " +
                "INNER JOIN T_USER u ON o.USER_ID = u.ID";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectFromTableSql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Order order = getOrder(resultSet);
                        orderList.add(order);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return orderList;
    }

    private Order getOrder(ResultSet resultSet) throws SQLException {
        User user = User.newBuilder()
                .withId(resultSet.getLong("USER_ID"))
                .withFirstName(resultSet.getString("FIRST_NAME"))
                .withLastName(resultSet.getString("LAST_NAME"))
                .build();

        Product product = new Product();
        product.setId(resultSet.getLong("productId"));
        product.setName(resultSet.getString("NAME"));
        product.setDescription(resultSet.getString("DESCRIPTION"));
        product.setPrice(resultSet.getBigDecimal("PRICE"));

        Order order = new Order();
        order.setOrderNumber(resultSet.getString("ORDER_NUMBER"));
        order.setId(resultSet.getLong("orderId"));
        order.setUser(user);
        order.setProduct(product);
        return order;
    }
}
