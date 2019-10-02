package com.electroeing.factorymethod.service;

import com.electroeing.factorymethod.dao.ProductDAO;
import com.electroeing.factorymethod.entities.Product;
import com.electroeing.factorymethod.enumerators.DBType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class DatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    @PostConstruct
    public void process() {
        final ProductDAO productDAO = new ProductDAO(DBType.MYSQL);

        final Product product1 = new Product(3L, "product3", 120.0);
        productDAO.saveProduct(product1);

        final List<Product> allProducts = productDAO.getAllProducts();
        for (Product p :
                allProducts) {
            logger.info("Product - {}", p);
        }
    }
}
