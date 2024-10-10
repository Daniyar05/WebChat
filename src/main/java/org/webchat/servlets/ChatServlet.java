package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;
import org.webchat.repository.ChatRepoImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ChatServlet", value = "/chat")

public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String idChat = request.getParameter("ID_CHAT");
        Optional<Chat> chatOptional= ChatRepoImpl.getChat(idChat);

        if (chatOptional.isEmpty()){
            return;
        }

        Chat thisChat = ChatRepoImpl.getChat(idChat).get();
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }
        request.setAttribute("chat", thisChat);
        request.getRequestDispatcher("/chat.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = ((User) request.getSession().getAttribute("user")).getId();
        String content = request.getParameter("content");
        if (author != null && content != null && !author.trim().isEmpty() && !content.trim().isEmpty()) {
            Message newMessage = new Message(author, content);
            ChatRepoImpl.addMessage(request.getParameter("ID_CHAT"), newMessage);
            response.sendRedirect(request.getContextPath() + "/chat?ID_CHAT=" + request.getParameter("ID_CHAT"));
        }
    }
}
