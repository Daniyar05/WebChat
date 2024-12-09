package org.webchat.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigurationBD {

    private final String USER;
    private final String PASSWORD;
    private final String URL;

    public ConfigurationBD(String user, String password, String url) {
        USER = user;
        PASSWORD = password;
        URL = url;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getURL() {
        return URL;
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
                properties.getProperty("db.url") + properties.getProperty("db.dbname")
        );
    }
}
