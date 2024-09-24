package org.webchat.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;
import org.webchat.repository.ChatsRepo;
import org.webchat.repository.UsersRepo;
import org.webchat.utils.ChatsLaunch;
import org.webchat.utils.FileLaunch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "ChatServlet", value = "/chat")

public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String idChat = request.getParameter("ID_CHAT");
        Chat thisChat = ChatsRepo.getChat(idChat);

        User thisUser = UsersRepo.getUser(request.getSession().getId());
        if (thisUser==null){
            UsersRepo.addUser(new User(request.getSession().getId()));
        }
        if (thisChat != null){
            request.setAttribute("chat", thisChat);
            request.getRequestDispatcher("/chat.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getSession().getId();
        String content = request.getParameter("content");
        if (author != null && content != null && !author.trim().isEmpty() && !content.trim().isEmpty()) {
            Message newMessage = new Message(author, content);
            Chat chat = ChatsRepo.getChat((String) request.getParameter("ID_CHAT"));
            chat.addMessage(newMessage);
            response.sendRedirect(request.getContextPath()+"/chat?ID_CHAT="+request.getParameter("ID_CHAT"));
            // Код для сохранения сообщения в базу данных или другом хранилище.

        }


    }

}
