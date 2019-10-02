package com.electroeing.factorymethod.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MongoDBAdapter implements IDBAdapter {
    private final static Logger logger = LoggerFactory.getLogger(MongoDBAdapter.class);

    static {
        try {
            Class.forName("mongodb.jdbc.MongoDriver");
        } catch (ClassNotFoundException e) {
            logger.error("Error registering mongo DB Driver", e);
        }
    }
    @Override
    public Connection getConnection() {
        try {
            final Connection connection = DriverManager.getConnection(getConnectionString(), "new_user", "some_password");
            logger.info("Connection class - {}", connection.getClass().getCanonicalName());
            return connection;
        } catch (SQLException e) {
            logger.error("Error connecting to MongoDB database", e);
            // TODO: Handle error Properly
            return null;
        }
    }

    public String getConnectionString() {
        return "jdbc:mongo://localhost:27017/factoryDB";
    }
}
