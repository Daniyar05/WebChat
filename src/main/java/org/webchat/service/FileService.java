package org.webchat.service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public interface FileService {

//    UUID updateFile(Part part);

    void downloadFile(String id, HttpServletResponse response);
    void updateFile(String userId, Part filePart);

    void deleteFile(String userId);
}
