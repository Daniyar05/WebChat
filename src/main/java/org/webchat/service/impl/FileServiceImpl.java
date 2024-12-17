package org.webchat.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.webchat.exception.IncorrectFileTypeException;
import org.webchat.service.FileService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.UUID;

@Slf4j
public class FileServiceImpl implements FileService {

    private final String path = System.getProperty("user.home")+"/avatars";
    private final String defaultImageName = "default";

    public FileServiceImpl() {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs(); // Создаёт директорию, если её нет
        }
    }

    @SneakyThrows
    public UUID updateFile(Part part) {
        UUID uuid = UUID.randomUUID();
        if(part != null && part.getSize() > 0) {
            String contentType = part.getContentType();
            if(!contentType.equalsIgnoreCase("image/jpeg")) {
                throw new IncorrectFileTypeException();
            }
            part.write(path + File.separator + uuid + ".jpg");
            return uuid;
        }
        return null;
    }

    @Override
    @SneakyThrows
    public void downloadFile(String id, HttpServletResponse response) {
        File imageFile = null;
        if(id != null) {
            imageFile = new File(path + File.separator + id + ".jpg");
        }
        if(imageFile == null || !imageFile.exists()) {
            imageFile = new File(path + File.separator + defaultImageName + ".jpg");
        }
        if (!imageFile.exists()){ // https://static-00.iconduck.com/assets.00/chat-icon-2048x2048-i7er18st.png
            response.sendRedirect("https://static-00.iconduck.com/assets.00/chat-icon-2048x2048-i7er18st.png");
            return;
        }
        FileInputStream fis = new FileInputStream(imageFile);
        OutputStream os = response.getOutputStream();

        response.setContentType("image/jpeg");

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }

        fis.close();
        os.close();
    }

    @Override
    @SneakyThrows
    public void updateFile(String userId, Part filePart) {
        if (filePart != null && filePart.getSize() > 0) {
            String contentType = filePart.getContentType();
            if (!contentType.equalsIgnoreCase("image/jpeg")) {
                throw new IncorrectFileTypeException();
            }

            File previousFile = new File(path + File.separator + userId + ".jpg");
            if (previousFile.exists()) {
                if (!previousFile.delete()) {
                    log.warn("Failed to delete old avatar for user: " + userId);
                }
            }
            filePart.write(path + File.separator + userId + ".jpg");
        }
    }

    @Override
    @SneakyThrows
    public void deleteFile(String userId) {
        if (userId != null) {
            File fileToDelete = new File(path + File.separator + userId + ".jpg");
            if (fileToDelete.exists()) {
                if (fileToDelete.delete()) {
                    log.info("Avatar deleted for user: {}", userId);
                } else {
                    log.warn("Failed to delete avatar for user: {}", userId);
                }
            } else {
                log.warn("No avatar found to delete for user: {}", userId);
            }
        }
    }
}
