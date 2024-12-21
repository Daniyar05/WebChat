package org.webchat.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.webchat.repository.MongoFileRepository;
import org.webchat.service.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@RequiredArgsConstructor
public class MongoFileService implements FileService {
    private final MongoFileRepository mongoFileRepository;
    private final String defaultImageUuid;

    @Override
    public void downloadFile(String id, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(id == null) {
            id = String.valueOf(defaultImageUuid);
        }
        try {
            byte[] data = mongoFileRepository.getFile(id);
            os.write(data);
        } catch (FileNotFoundException e) {
            byte[] data = mongoFileRepository.getFile(defaultImageUuid);
            os.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateFile(String chatId, Part filePart) {
        try {
            mongoFileRepository.saveFile(chatId, filePart.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String chatId) {
        mongoFileRepository.delete(chatId);
    }
}
