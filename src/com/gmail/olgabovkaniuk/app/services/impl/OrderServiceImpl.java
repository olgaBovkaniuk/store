package com.gmail.olgabovkaniuk.app.services.impl;

import com.gmail.olgabovkaniuk.app.dao.OrderDao;
import com.gmail.olgabovkaniuk.app.dao.connection.ConnectionService;
import com.gmail.olgabovkaniuk.app.dao.impl.OrderDaoImpl;
import com.gmail.olgabovkaniuk.app.dao.model.Order;
import com.gmail.olgabovkaniuk.app.services.OrderService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public Order save(Order order) {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            order.setOrderNumber(String.valueOf(new Date().getTime()));
            Order savedOrder = orderDao.save(connection, order);
            connection.commit();
            return savedOrder;
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

    @Override
    public List<Order> findUserOrders(Long userId) {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            List<Order> orderList = orderDao.findUserOrders(connection, userId);
            connection.commit();
            return orderList;
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

    @Override
    public boolean delete(String orderId) {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            orderDao.delete(connection, orderId);
            connection.commit();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Order> selectAllOrders() {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            List<Order> savedUserList = orderDao.selectAllOrders(connection);
            connection.commit();
            return savedUserList;
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
}
