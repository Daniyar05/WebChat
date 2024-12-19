package org.webchat.repository;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.UUID;

public interface MongoFileRepository {

    String saveFile(String id, InputStream inputStream);

    byte[] getFile(String id);

    void delete(String chatId);
}