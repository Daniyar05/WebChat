package org.webchat.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DatabaseConnection {



    public DataSource getDataSource() {
        return dataSource;
    }

    DataSource dataSource;


    public DatabaseConnection() {
        dataSource=getDatasource();
    }

    private DataSource getDatasource() {
        HikariConfig config = new HikariConfig();
        Configuration configuration = Configuration.getConnection();
        config.setUsername(configuration.getUSER());
        config.setPassword(configuration.getPASSWORD());
        config.setJdbcUrl(configuration.getURL());
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();

    }
}