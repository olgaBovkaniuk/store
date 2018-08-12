package com.gmail.olgabovkaniuk.app.services.parser;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

public class ProductRecord {
    private String name;
    private String description;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name = "Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @XmlElement(name = "Price")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
