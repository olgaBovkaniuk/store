package com.gmail.olgabovkaniuk.app.services;

import com.gmail.olgabovkaniuk.app.dao.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    void save(Product product);

    void saveAll(List<Product> products);

    void uploadProducts();

}
