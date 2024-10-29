package org.webchat.servlets;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;
import org.webchat.repository.ChatRepoImpl;
import org.webchat.repository.UsersRepoImpl;

import java.io.IOException;
import java.util.List;
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

        Chat thisChat = chatOptional.get();
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }

        request.setAttribute("chat", thisChat);
        request.setAttribute("messagesJson", new Gson().toJson(thisChat.getHistory()));
        request.getRequestDispatcher("/chat.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorId = ((String) request.getSession().getAttribute("userId"));
        Optional<User> user = UsersRepoImpl.getUser(authorId);

        String content = request.getParameter("content");

        if (authorId != null && content != null && !content.trim().isEmpty() && user.isPresent()) {
            Message newMessage = new Message(user.get(), content);
            ChatRepoImpl.addMessage(request.getParameter("ID_CHAT"), newMessage);
            response.sendRedirect(request.getContextPath() + "/chat?ID_CHAT=" + request.getParameter("ID_CHAT"));
        }
    }
}
