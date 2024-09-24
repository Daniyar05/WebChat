package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.Chat;
import org.webchat.repository.ChatsRepo;
import org.webchat.repository.UsersRepo;

import java.io.IOException;

@WebServlet(name = "CreateChatServlet", value = "/create-chat")

public class CreateChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Chat newChat = new Chat();
        UsersRepo.getUser(request.getSession().getId()).addChat(newChat);
        ChatsRepo.addChat(newChat);
        response.sendRedirect(request.getContextPath()+"/list-chats");
    }
}
