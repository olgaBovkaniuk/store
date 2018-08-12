package com.gmail.olgabovkaniuk.app.services;

import com.gmail.olgabovkaniuk.app.dao.model.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order);

    List<Order> findUserOrders(Long userId);

    boolean delete(Long orderId);

    List<Order> selectAllOrders();
}
