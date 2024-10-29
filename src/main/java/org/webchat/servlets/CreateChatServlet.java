package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.Chat;
import org.webchat.repository.ChatRepoImpl;
import org.webchat.repository.UsersRepoImpl;

import java.io.IOException;

@WebServlet(name = "CreateChatServlet", value = "/create-chat")

public class CreateChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Chat newChat = new Chat();
        UsersRepoImpl.addUserChat(request.getParameter("ID_USER"),newChat.getIdChat());
        ChatRepoImpl.addChat(newChat);

        response.sendRedirect(request.getContextPath() + "/list-chats");
    }

}
