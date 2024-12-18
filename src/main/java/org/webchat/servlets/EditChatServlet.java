package org.webchat.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.repository.ChatRepo;

import java.io.IOException;

@WebServlet(name = "EditChatServlet", value = "/edit-chat")
public class EditChatServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException { // rename chat
        String newUsername = request.getParameter("name");
        String idChat = request.getParameter("chatId");

        ((ChatRepo) getServletContext().getAttribute("chatRepo")).renameChat(idChat, newUsername);
        response.sendRedirect(request.getContextPath() + "/chat?ID_CHAT=" + idChat);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException { // delete chat
        String idChat = request.getHeader("chatId");
        ((ChatRepo) getServletContext().getAttribute("chatRepo")).deleteChat(idChat);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}


