package org.webchat.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.repository.ChatRepoImpl;

import java.io.IOException;

@WebServlet(name = "EditChatServlet", value = "/edit-chat")
public class EditChatServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newUsername = request.getParameter("name");
        String idChat = request.getParameter("chatId");
        ChatRepoImpl.renameChat(idChat, newUsername);
        response.sendRedirect(request.getContextPath() + "/chat?ID_CHAT=" + idChat);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idChat = request.getHeader("chatId");
        ChatRepoImpl.deleteChat(idChat);
        response.sendRedirect("/list-chats");
    }

}


