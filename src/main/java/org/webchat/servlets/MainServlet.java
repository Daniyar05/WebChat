package org.webchat.servlets;

import java.io.*;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.webchat.utils.FileLaunch;

@WebServlet(name = "helloServlet", value = "/hello")
public class MainServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        BufferedReader reader = new BufferedReader(FileLaunch.loadFile(request, "/main-screen.html"));
        FileLaunch.launchHtml(reader,writer);
        request.getSession().setMaxInactiveInterval(-1);
        writer.close();
        reader.close();

    }

    public void destroy() {
    }
}