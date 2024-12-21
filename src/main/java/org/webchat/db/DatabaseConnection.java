package org.webchat.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import javax.sql.DataSource;
import java.sql.*;

@Getter
public class DatabaseConnection {
    DataSource dataSource;

    public DatabaseConnection() {
        dataSource=getDatasource();
    }

    private DataSource getDatasource() {
        HikariConfig config = new HikariConfig();
        ConfigurationBD configuration = ConfigurationBD.getConnection();
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

    public MongoClient getMongoClient(){
        ConfigurationBD configuration = ConfigurationBD.getConnection();
        MongoClientURI uri = new MongoClientURI("mongodb://root:12345678@%s:%s/".formatted(configuration.getMONGO_HOST(), Integer.parseInt(configuration.getMONGO_PORT())));
        return new MongoClient(uri);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public void close(){
        ((HikariDataSource) dataSource).close();
    }
}