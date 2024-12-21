package org.webchat.utils;

import org.slf4j.Logger;
import org.webchat.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ChatCleaner {

    DatabaseConnection databaseConnection;
    private final Logger logger;


    public ChatCleaner(DatabaseConnection databaseConnection, Logger logger) {
        this.databaseConnection = databaseConnection;
        this.logger = logger;
    }


    public void deleteOldChats() {
        String query = "DELETE FROM chats WHERE created_at < NOW() - INTERVAL '1 day' ;";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (Exception e) {
            logger.error("Ошибка при удалении старых чатов: {}", e.getMessage());
        }
    }

    public void start(int intervalMilliSec) {
        Thread cleanerThread = new Thread(() -> {
            while (true) {
                try {
                    deleteOldChats();
                    Thread.sleep(intervalMilliSec);
                } catch (Exception e) {
                    logger.error("Ошибка в процессе очистки - {}", e.getMessage());
                }
            }
        });
        cleanerThread.setDaemon(true);
        cleanerThread.start();
        logger.info("Асинхронное удаление запущено");

    }
}
