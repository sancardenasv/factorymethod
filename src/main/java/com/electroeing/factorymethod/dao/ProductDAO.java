package com.electroeing.factorymethod.dao;

import com.electroeing.factorymethod.entities.Product;
import com.electroeing.factorymethod.enumerators.DBType;
import com.electroeing.factorymethod.factory.DBAdapterFactory;
import com.electroeing.factorymethod.factory.IDBAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDAO {
    private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);

    private IDBAdapter adapter;

    public ProductDAO(DBType dbType) {
        adapter = DBAdapterFactory.getAdapter(dbType);
    }

    public void saveProduct(Product product) {
        final String sql = "insert into products (id, name, price) values (?, ?, ?)";

        try (final PreparedStatement preparedStatement = adapter.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing query", e);
        }
    }

    public List<Product> getAllProducts() {
        String sql = "select * from products";
        try (final PreparedStatement preparedStatement = adapter.getConnection().prepareStatement(sql);
             final ResultSet results = preparedStatement.executeQuery()) {
            List<Product> productList = new ArrayList<>();
            while (results.next()) {
                productList.add(new Product(results.getLong("id"), results.getString("name"), results.getDouble("price")));
            }
            return productList;
        } catch (SQLException e) {
            logger.error("Error executing query", e);
            return Collections.emptyList();
        }
    }
}
