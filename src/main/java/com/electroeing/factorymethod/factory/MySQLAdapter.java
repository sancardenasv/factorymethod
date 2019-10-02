package com.electroeing.factorymethod.factory;

import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class MySQLAdapter implements IDBAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MySQLAdapter.class);

    @Value("${application.database.mysql.user}")
    private String user;
    @Value("${application.database.mysql.password}")
    private String password;
    @Value("${application.database.mysql.connection-string}")
    private String connectionString;

    static {
        try {
            new Driver();
        } catch (SQLException e) {
            logger.error("Error registering MySQL Driver", e);
        }
    }

    @Override
    public Connection getConnection() {
        try {
            final Connection connection = DriverManager.getConnection(connectionString, user, password);
            logger.info("Connection class - {}", connection.getClass().getCanonicalName());
            return connection;
        } catch (SQLException e) {
            logger.error("Error connecting to MySql database", e);
            // TODO: Handle error Properly
            return null;
        }
    }
}
