package com.electroeing.factorymethod.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class MongoDBAdapter implements IDBAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MongoDBAdapter.class);

    @Value("${application.database.mongodb.user}")
    private String user;
    @Value("${application.database.mongodb.password}")
    private String password;
    @Value("${application.database.mongodb.connection-string}")
    private String connectionString;

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
            final Connection connection = DriverManager.getConnection(connectionString, user, password);
            logger.info("Connection class - {}", connection.getClass().getCanonicalName());
            return connection;
        } catch (SQLException e) {
            logger.error("Error connecting to MongoDB database", e);
            // TODO: Handle error Properly
            return null;
        }
    }
}
