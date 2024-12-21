package org.webchat.config;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ConfigurationChat {
    private final int RELOAD;
    private final int LIMIT_MESSAGE;

    public ConfigurationChat(int RELOAD, int LIMIT_MESSAGE) {
        this.RELOAD = RELOAD;
        this.LIMIT_MESSAGE = LIMIT_MESSAGE;
    }


    public static ConfigurationChat getConfig(){
        Properties properties = new Properties();
        try (InputStream input = ConfigurationChat.class.getClassLoader().getResourceAsStream("chat.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find chat.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load db.properties", e);
        }
        return new ConfigurationChat(
                Integer.parseInt(properties.getProperty("chat.reload")),
                Integer.parseInt(properties.getProperty("chat.limit"))
        );
    }
}
