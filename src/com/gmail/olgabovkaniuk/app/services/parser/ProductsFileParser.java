package com.gmail.olgabovkaniuk.app.services.parser;

import com.gmail.olgabovkaniuk.app.dao.model.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ProductsFileParser {
    private ProductRecordsToProductsConverter converter = new ProductRecordsToProductsConverter();

    public List<Product> parse(String pathToFile) {
        try {
            File file = new File(pathToFile);
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductStore.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ProductStore productStore = (ProductStore) jaxbUnmarshaller.unmarshal(file);
            return converter.convert(productStore.getProductList());
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
