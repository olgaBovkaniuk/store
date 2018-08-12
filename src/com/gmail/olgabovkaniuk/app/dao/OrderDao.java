package com.gmail.olgabovkaniuk.app.dao;

import com.gmail.olgabovkaniuk.app.dao.model.Order;

import java.sql.Connection;
import java.util.List;

public interface OrderDao {
    Order save(Connection connection, Order order);

    List<Order> findUserOrders(Connection connection, Long userId);

    boolean delete(Connection connection, Long orderId);

    List<Order> selectAllOrders(Connection connection);
}
