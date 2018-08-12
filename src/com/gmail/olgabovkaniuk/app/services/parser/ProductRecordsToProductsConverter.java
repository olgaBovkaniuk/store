package com.gmail.olgabovkaniuk.app.services.parser;

import com.gmail.olgabovkaniuk.app.dao.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRecordsToProductsConverter {

    public List<Product> convert(List<ProductRecord> productRecords) {
        List<Product> products = new ArrayList<>();
        for (ProductRecord productRecord : productRecords) {
            Product product = new Product();
            product.setName(productRecord.getName());
            product.setDescription(productRecord.getDescription());
            product.setPrice(productRecord.getPrice());
            products.add(product);
        }
        return products;
    }
}
