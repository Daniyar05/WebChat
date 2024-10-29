package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.repository.ChatRepoImpl;

import java.io.IOException;

@WebServlet(name = "DeleteChatServlet", value = "/delete-chat")
public class DeleteChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idChat = request.getParameter("chatId");
        ChatRepoImpl.deleteChat(idChat);
        response.sendRedirect(request.getContextPath()+"/list-chats");
    }
}
