package org.webchat.service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

public interface FileService {

    void downloadFile(String id, HttpServletResponse response) throws IOException;
    void updateFile(String userId, Part filePart);
    void deleteFile(String userId);

}
