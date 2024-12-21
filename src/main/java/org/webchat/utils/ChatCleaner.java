package org.webchat.utils;

import org.slf4j.Logger;
import org.webchat.db.DatabaseConnection;
import org.webchat.repository.ChatRepo;

public class ChatCleaner {

    private final Logger logger;
    private final ChatRepo chatRepo;

    public ChatCleaner(Logger logger, ChatRepo chatRepo) {
        this.logger = logger;
        this.chatRepo = chatRepo;
    }


    public void deleteOldChats() {
        chatRepo.deleteOldChats();
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
