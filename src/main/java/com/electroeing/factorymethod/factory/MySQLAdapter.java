package com.electroeing.factorymethod.factory;

import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLAdapter implements IDBAdapter {
    private final static Logger logger = LoggerFactory.getLogger(MySQLAdapter.class);

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
            final Connection connection = DriverManager.getConnection(getConnectionString(), "root", "example");
            logger.info("Connection class - {}", connection.getClass().getCanonicalName());
            return connection;
        } catch (SQLException e) {
            logger.error("Error connecting to MySql database", e);
            // TODO: Handle error Properly
            return null;
        }
    }

    public String getConnectionString() {
        return "jdbc:mysql://localhost:3306/factoryDB?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
    }
}
