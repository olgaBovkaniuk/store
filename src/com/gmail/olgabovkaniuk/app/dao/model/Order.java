package com.gmail.olgabovkaniuk.app.dao.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Order extends Identifier {
    private String orderNumber;
    private User user;
    private Product product;
    private BigDecimal totalPrice;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderNumber, order.orderNumber) &&
                Objects.equals(user, order.user) &&
                Objects.equals(product, order.product) &&
                Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, user, product, totalPrice);
    }
}
