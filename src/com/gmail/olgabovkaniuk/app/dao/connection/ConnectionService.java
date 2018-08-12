package com.gmail.olgabovkaniuk.app.dao.connection;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    private static ConnectionService instance;
    private static Connection connection;

    private ConnectionService() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        System.out.println("-------- MySQL JDBC Connection Testing ------------");
        try {
            Class.forName(configurationManager.getProperty(ConfigurationManager.DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    configurationManager.getProperty(ConfigurationManager.DATABASE_URL),
                    configurationManager.getProperty(ConfigurationManager.DATABASE_USERNAME),
                    configurationManager.getProperty(ConfigurationManager.DATABASE_PWD)
            );
            System.out.println("Connection has been created");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    public static ConnectionService getInstance() {
        if (instance == null) {
            instance = new ConnectionService();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }
}
