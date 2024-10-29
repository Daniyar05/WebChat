package org.webchat.servlets;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SettingsChatServlet", value = "/settings-chat")

public class SettingsChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        response.sendRedirect(request.getContextPath() + "/list-chats");
    }

}
