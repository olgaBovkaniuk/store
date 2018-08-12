package com.gmail.olgabovkaniuk.app.services.parser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "ProductStore")
public class ProductStore {

    private List<ProductRecord> productList = new ArrayList<>();

    public List<ProductRecord> getProductList() {
        return productList;
    }

    @XmlElement(name = "ProductRecord")
    public void setProductList(List<ProductRecord> productList) {
        this.productList = productList;
    }
}
