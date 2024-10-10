package org.webchat.utils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import java.io.*;

public class FileLaunch {
    public static void launchHtml(BufferedReader reader, PrintWriter writer) throws IOException {
        String line = reader.readLine();
        while (line != null) {
            writer.println(line);
            line = reader.readLine();
        }
    }


    public static FileReader loadFile(HttpServletRequest request, String relativePath) {
        ServletContext sc = request.getServletContext();
        try {
            return new FileReader(sc.getRealPath(relativePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
