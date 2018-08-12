package com.gmail.olgabovkaniuk.app.dao.model;

import java.util.Objects;

public class OrderedProduct extends Identifier{
    private long orderId;
    private long productId;
    private int productsAmount;

    public OrderedProduct(long orderId, long productId, int productsAmount) {
        this.orderId = orderId;
        this.productId = productId;
        this.productsAmount = productsAmount;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getProductId() {
        return productId;
    }

    public int getProductsAmount() {
        return productsAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedProduct that = (OrderedProduct) o;
        return orderId == that.orderId &&
                productId == that.productId &&
                productsAmount == that.productsAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, productsAmount);
    }
}
