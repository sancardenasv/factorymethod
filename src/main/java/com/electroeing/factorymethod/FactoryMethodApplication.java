package com.electroeing.factorymethod;

import com.electroeing.factorymethod.dao.ProductDAO;
import com.electroeing.factorymethod.entities.Product;
import com.electroeing.factorymethod.enumerators.DBType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FactoryMethodApplication {
    private final static Logger logger = LoggerFactory.getLogger(FactoryMethodApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FactoryMethodApplication.class, args);

        final Product product1 = new Product(3l, "product3", 120.0);
        final ProductDAO productDAO = new ProductDAO(DBType.MONGODB);
        productDAO.saveProduct(product1);

        final List<Product> allProducts = productDAO.getAllProducts();
        for (Product p :
                allProducts) {
            logger.info("Product - {}", p);
        }
    }

}
