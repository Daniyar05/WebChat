package org.webchat.db;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ConfigurationBD {

    private final String USER;
    private final String PASSWORD;
    private final String URL;

    private final String MONGO_HOST;
    private final String MONGO_PORT;


    public ConfigurationBD(String user, String password, String url, String mongoHost, String mongoPort) {
        USER = user;
        PASSWORD = password;
        URL = url;
        MONGO_HOST = mongoHost;
        MONGO_PORT = mongoPort;
    }

    public static ConfigurationBD getConnection(){
        Properties properties = new Properties();
        try (InputStream input = ConfigurationBD.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find db.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load db.properties", e);
        }
        return new ConfigurationBD(
                properties.getProperty("db.user"),
                properties.getProperty("db.password"),
                properties.getProperty("db.url") + properties.getProperty("db.dbname"),
                properties.getProperty("mongo.host"),
                properties.getProperty("mongo.port")
                );
    }
}
